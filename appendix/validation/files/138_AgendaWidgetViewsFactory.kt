package io.ipoli.android.quest.widget.agenda

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.support.annotation.ColorInt
import android.support.v4.content.ContextCompat
import android.text.format.DateFormat
import android.view.View
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.mikepenz.entypo_typeface_library.Entypo
import com.mikepenz.google_material_typeface_library.GoogleMaterial
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.ionicons_typeface_library.Ionicons
import io.ipoli.android.Constants
import io.ipoli.android.MyPoliApp
import io.ipoli.android.R
import io.ipoli.android.common.di.BackgroundModule
import io.ipoli.android.common.text.QuestStartTimeFormatter
import io.ipoli.android.common.view.AndroidColor
import io.ipoli.android.common.view.AndroidIcon
import io.ipoli.android.common.view.listItemIcon
import io.ipoli.android.event.Event
import io.ipoli.android.event.usecase.FindEventsBetweenDatesUseCase
import io.ipoli.android.player.data.Player
import io.ipoli.android.quest.Quest
import org.threeten.bp.LocalDate
import space.traversal.kapsule.Injects
import space.traversal.kapsule.inject
import space.traversal.kapsule.required
import java.util.concurrent.CopyOnWriteArrayList


/**
 * Created by Venelin Valkov <venelin@mypoli.fun>
 * on 02/10/2018.
 */
class AgendaWidgetViewsFactory(private val context: Context) :
    RemoteViewsService.RemoteViewsFactory, Injects<BackgroundModule> {

    sealed class Item {
        data class QuestItem(val quest: Quest) : Item()
        data class EventItem(val event: Event) : Item()
    }

    private var items = CopyOnWriteArrayList<Item>()

    private val questRepository by required { questRepository }
    private val sharedPreferences by required { sharedPreferences }
    private val findEventsBetweenDatesUseCase by required { findEventsBetweenDatesUseCase }

    private var use24HourFormat = false

    override fun onCreate() {
        inject(MyPoliApp.backgroundModule(context))
    }

    override fun getLoadingView() = null

    override fun getItemId(position: Int): Long = position.toLong()

    override fun onDataSetChanged() {

        use24HourFormat = shouldUse24HourFormat()

        val widgetItems = mutableListOf<Item>()

        widgetItems.addAll(questRepository.findScheduledAt(LocalDate.now())
            .filter { !it.isCompleted }.map {
                Item.QuestItem(
                    it
                )
            })

        widgetItems.addAll(
            findEventsBetweenDatesUseCase.execute(
                FindEventsBetweenDatesUseCase.Params(LocalDate.now(), LocalDate.now())
            ).map { Item.EventItem(it) })

        widgetItems.sortBy {
            when (it) {
                is Item.QuestItem ->
                    it.quest.startTime?.toMinuteOfDay()
                is Item.EventItem ->
                    it.event.startTime.toMinuteOfDay()
            }
        }

        items.clear()
        items.addAll(widgetItems)
    }

    override fun hasStableIds() = true

    override fun getViewAt(position: Int) =

        items[position].let {
            when (it) {
                is Item.QuestItem -> {

                    val q = it.quest

                    RemoteViews(context.packageName, R.layout.item_widget_agenda).apply {

                        setTextViewText(R.id.widgetQuestName, q.name)
                        setTextViewText(
                            R.id.widgetQuestStartTime,
                            QuestStartTimeFormatter.formatWithDuration(q, context, use24HourFormat)
                        )

                        if (q.tags.isEmpty()) {
                            setViewVisibility(R.id.widgetTagIndicator, View.GONE)
                            setViewVisibility(R.id.widgetTag, View.GONE)
                        } else {
                            setViewVisibility(R.id.widgetTagIndicator, View.VISIBLE)
                            setViewVisibility(R.id.widgetTag, View.VISIBLE)
                            val tag = q.tags.first()
                            setTextViewText(R.id.widgetTag, tag.name)

                            val bgColor = AndroidColor.valueOf(tag.color.name).color500
                            val indicator =
                                IconicsDrawable(context)
                                    .icon(GoogleMaterial.Icon.gmd_block)
                                    .colorRes(bgColor)
                                    .backgroundColorRes(bgColor)
                                    .roundedCornersDp(4)
                                    .sizeDp(8)

                            setImageViewBitmap(R.id.widgetTagIndicator, indicator.toBitmap())
                        }

                        val icon = q.icon?.let { AndroidIcon.valueOf(it.name).icon }
                            ?: Ionicons.Icon.ion_checkmark

                        val iconDrawable =
                            IconicsDrawable(context).listItemIcon(icon)

                        setImageViewBitmap(R.id.widgetQuestIcon, iconDrawable.toBitmap())

                        val iconBgColor = AndroidColor.valueOf(q.color.name).color500

                        setImageViewBitmap(
                            R.id.widgetQuestIconBackground,
                            createIconBackground(ContextCompat.getColor(context, iconBgColor))
                        )

                        setOnClickFillInIntent(
                            R.id.widgetSelectableBackground,
                            createClickQuestIntent(q)
                        )

                        setViewVisibility(R.id.widgetQuestComplete, View.VISIBLE)
                        val checkboxIcon =
                            IconicsDrawable(context)
                                .icon(Ionicons.Icon.ion_android_checkbox_outline_blank)
                                .colorRes(R.color.md_light_text_70)
                                .sizeDp(24)
                        setImageViewBitmap(R.id.widgetQuestComplete, checkboxIcon.toBitmap())
                        setOnClickFillInIntent(
                            R.id.widgetQuestComplete,
                            createCompleteQuestIntent(q)
                        )
                    }
                }

                is Item.EventItem -> {
                    val e = it.event

                    RemoteViews(context.packageName, R.layout.item_widget_agenda).apply {
                        setTextViewText(R.id.widgetQuestName, e.name)
                        setTextViewText(R.id.widgetQuestStartTime, formatStartTime(e))

                        val iconDrawable =
                            IconicsDrawable(context).listItemIcon(Entypo.Icon.ent_calendar)

                        setImageViewBitmap(R.id.widgetQuestIcon, iconDrawable.toBitmap())

                        setImageViewBitmap(
                            R.id.widgetQuestIconBackground,
                            createIconBackground(e.color)
                        )

                        setViewVisibility(R.id.widgetQuestComplete, View.GONE)
                    }
                }

            }
        }

    private fun createClickQuestIntent(q: Quest): Intent {
        val b = Bundle().apply {
            putInt(
                AgendaWidgetProvider.QUEST_ACTION_EXTRA_KEY,
                AgendaWidgetProvider.QUEST_ACTION_VIEW
            )
            putString(Constants.QUEST_ID_EXTRA_KEY, q.id)
        }

        return Intent().putExtras(b)
    }

    private fun createCompleteQuestIntent(q: Quest): Intent {
        val b = Bundle().apply {
            putInt(
                AgendaWidgetProvider.QUEST_ACTION_EXTRA_KEY,
                AgendaWidgetProvider.QUEST_ACTION_COMPLETE
            )
            putString(Constants.QUEST_ID_EXTRA_KEY, q.id)
        }

        return Intent().putExtras(b)
    }

    private fun createIconBackground(@ColorInt iconBgColor: Int): Bitmap? {
        val drawable =
            context.getDrawable(R.drawable.widget_agenda_item_icon_background) as GradientDrawable
        drawable.setColor(iconBgColor)

        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )

        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

    private fun formatStartTime(event: Event): String {
        val start = event.startTime
        val end = start.plus(event.duration.intValue)
        return "${start.toString(use24HourFormat)} - ${end.toString(use24HourFormat)}"
    }

    private fun shouldUse24HourFormat(): Boolean {
        val timeFormat = Player.Preferences.TimeFormat.valueOf(
            sharedPreferences.getString(
                Constants.KEY_TIME_FORMAT,
                Player.Preferences.TimeFormat.DEVICE_DEFAULT.name
            )
        )
        if (timeFormat == Player.Preferences.TimeFormat.DEVICE_DEFAULT) {
            return DateFormat.is24HourFormat(context)
        }

        if (timeFormat == Player.Preferences.TimeFormat.TWELVE_HOURS) {
            return false
        }

        return true
    }

    override fun getCount() = items.size

    override fun getViewTypeCount() = 1

    override fun onDestroy() {
    }

}