package io.ipoli.android.challenge.show

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.support.annotation.ColorRes
import android.support.design.widget.AppBarLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.*
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.CalendarView
import com.mikepenz.google_material_typeface_library.GoogleMaterial
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.iconics.typeface.IIcon
import io.ipoli.android.Constants
import io.ipoli.android.Constants.Companion.DECIMAL_FORMATTER
import io.ipoli.android.MainActivity
import io.ipoli.android.R
import io.ipoli.android.challenge.entity.Challenge
import io.ipoli.android.common.ViewUtils
import io.ipoli.android.common.chart.AwesomenessScoreMarker
import io.ipoli.android.common.datetime.datesBetween
import io.ipoli.android.common.redux.android.ReduxViewController
import io.ipoli.android.common.text.DateFormatter
import io.ipoli.android.common.view.*
import io.ipoli.android.common.view.anim.AccelerateDecelerateEasingFunction
import io.ipoli.android.common.view.recyclerview.*
import io.ipoli.android.quest.Quest
import io.ipoli.android.quest.RepeatingQuest
import io.ipoli.android.tag.Tag
import kotlinx.android.synthetic.main.controller_challenge.view.*
import kotlinx.android.synthetic.main.item_challenge_average_value.view.*
import kotlinx.android.synthetic.main.item_challenge_progress.view.*
import kotlinx.android.synthetic.main.item_challenge_quest.view.*
import kotlinx.android.synthetic.main.item_challenge_target_value.view.*
import kotlinx.android.synthetic.main.item_quest_tag_list.view.*
import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth
import org.threeten.bp.format.TextStyle
import java.util.*


/**
 * Created by Venelin Valkov <venelin@mypoli.fun>
 * on 03/05/2018.
 */
class ChallengeViewController(args: Bundle? = null) :
    ReduxViewController<ChallengeAction, ChallengeViewState, ChallengeReducer>(args) {

    override val reducer = ChallengeReducer

    private var challengeId = ""
    private var showEdit = true
    private var showComplete = true

    private val appBarOffsetListener = object :
        AppBarStateChangeListener() {
        override fun onStateChanged(appBarLayout: AppBarLayout, state: State) {

            appBarLayout.post {
                if (state == State.EXPANDED) {
                    val supportActionBar = (activity as MainActivity).supportActionBar
                    supportActionBar?.setDisplayShowTitleEnabled(false)
                } else if (state == State.COLLAPSED) {
                    val supportActionBar = (activity as MainActivity).supportActionBar
                    supportActionBar?.setDisplayShowTitleEnabled(true)
                }
            }
        }
    }

    constructor(
        challengeId: String
    ) : this() {
        this.challengeId = challengeId
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup,
        savedViewState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        applyStatusBarColors = false
        val view = inflater.inflate(R.layout.controller_challenge, container, false)
        setToolbar(view.toolbar)
        view.collapsingToolbarContainer.isTitleEnabled = false

        view.questList.layoutManager = LinearLayoutManager(container.context)
        view.questList.adapter = QuestAdapter()

        view.habitList.layoutManager = LinearLayoutManager(container.context)
        view.habitList.adapter = HabitAdapter()

        val questSwipeHandler = object : SimpleSwipeCallback(
            R.drawable.ic_done_white_24dp,
            R.color.md_green_500,
            R.drawable.ic_delete_white_24dp,
            R.color.md_red_500
        ) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (direction == ItemTouchHelper.START) {
                    dispatch(ChallengeAction.RemoveQuestFromChallenge(viewHolder.adapterPosition))
                }
            }

            override fun getSwipeDirs(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ) = ItemTouchHelper.START
        }
        val questTouchHelper = ItemTouchHelper(questSwipeHandler)
        questTouchHelper.attachToRecyclerView(view.questList)

        val habitSwipeHandler = object : SimpleSwipeCallback(
            R.drawable.ic_done_white_24dp,
            R.color.md_green_500,
            R.drawable.ic_delete_white_24dp,
            R.color.md_red_500
        ) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (direction == ItemTouchHelper.START) {
                    dispatch(ChallengeAction.RemoveHabitFromChallenge(habitId(viewHolder)))
                }
            }

            private fun habitId(holder: RecyclerView.ViewHolder): String {
                val adapter = view.habitList.adapter as HabitAdapter
                return adapter.getItemAt(holder.adapterPosition).id
            }

            override fun getSwipeDirs(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ) = ItemTouchHelper.START
        }

        val habitTouchHelper = ItemTouchHelper(habitSwipeHandler)
        habitTouchHelper.attachToRecyclerView(view.habitList)

        view.addQuests.onDebounceClick {
            navigateFromRoot().toQuestPicker(challengeId)
        }

        view.trackedValueList.layoutManager = LinearLayoutManager(view.context)
        view.trackedValueList.isNestedScrollingEnabled = false
        view.trackedValueList.addItemDecoration(TopMarginDecoration(8))
        view.trackedValueList.adapter = TrackedValueAdapter()

        view.appbar.addOnOffsetChangedListener(appBarOffsetListener)

        return view
    }

    private fun setupLineChart(chart: LineChart) {
        with(chart) {

            description = null
            setExtraOffsets(0f, 0f, 0f, 0f)
            isDoubleTapToZoomEnabled = false

            setTouchEnabled(true)
            setPinchZoom(false)
            extraTopOffset = 16f
            extraBottomOffset = 16f

            setDrawGridBackground(false)

            axisRight.axisMinimum = 0f
            axisRight.spaceTop = 0f
            axisRight.granularity = 1f
            axisRight.setDrawAxisLine(false)
            axisRight.textSize = ViewUtils.spToPx(4, activity!!).toFloat()
            axisRight.textColor = colorRes(colorTextSecondaryResource)
            axisRight.setValueFormatter { value, _ -> "    ${value.toInt()}" }

            axisLeft.isEnabled = false

            xAxis.yOffset = ViewUtils.dpToPx(2f, activity!!)
            xAxis.isGranularityEnabled = true
            xAxis.granularity = 1f
            xAxis.textSize = ViewUtils.spToPx(4, activity!!).toFloat()
            xAxis.textColor = colorRes(colorTextSecondaryResource)
            xAxis.setDrawGridLines(false)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.setAvoidFirstLastClipping(true)
            xAxis.setDrawAxisLine(false)

            xAxis.labelRotationAngle = 335f

            legend.textColor = colorRes(colorTextSecondaryResource)
            legend.textSize = ViewUtils.spToPx(5, activity!!).toFloat()
            legend.form = Legend.LegendForm.CIRCLE
            legend.xEntrySpace = ViewUtils.dpToPx(4f, activity!!)

            setDrawBorders(false)
        }
    }

    private fun setupBarChart(chart: CombinedChart) {
        with(chart) {

            description = null
            setExtraOffsets(0f, 0f, 0f, 0f)
            isDoubleTapToZoomEnabled = false
            setDrawValueAboveBar(true)

            setTouchEnabled(true)
            setPinchZoom(false)
            extraTopOffset = 16f
            extraBottomOffset = 16f

            setDrawGridBackground(false)

            axisRight.spaceTop = 0f
            axisRight.granularity = 1f
            axisRight.setDrawAxisLine(false)
            axisRight.textSize = ViewUtils.spToPx(4, activity!!).toFloat()
            axisRight.textColor = colorRes(colorTextSecondaryResource)
            axisRight.setValueFormatter { value, _ -> "    ${value.toInt()}" }

            axisLeft.isEnabled = false

//            xAxis.yOffset = ViewUtils.dpToPx(-2f, activity!!)
            xAxis.isGranularityEnabled = true
            xAxis.granularity = 1f
            xAxis.textSize = ViewUtils.spToPx(4f, activity!!).toFloat()
            xAxis.textColor = colorRes(colorTextSecondaryResource)
            xAxis.setDrawGridLines(false)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.setAvoidFirstLastClipping(true)
            xAxis.setDrawAxisLine(false)

            xAxis.labelRotationAngle = 335f

            legend.textColor = colorRes(colorTextSecondaryResource)
            legend.textSize = ViewUtils.spToPx(5, activity!!).toFloat()
            legend.form = Legend.LegendForm.CIRCLE
            legend.xEntrySpace = ViewUtils.dpToPx(4f, activity!!)

            setDrawBorders(false)
        }
    }

    override fun onCreateLoadAction() = ChallengeAction.Load(challengeId)

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.challenge_menu, menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.actionComplete).isVisible = showComplete
        menu.findItem(R.id.actionEdit).isVisible = showEdit
        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {

            android.R.id.home ->
                router.handleBack()

            R.id.actionComplete -> {
                navigate().toConfirmation(
                    stringRes(R.string.dialog_confirmation_title),
                    stringRes(R.string.dialog_complete_challenge_message)
                ) {
                    dispatch(ChallengeAction.Complete(challengeId))
                    router.handleBack()
                }
                true
            }

            R.id.actionEdit -> {
                showEdit()
                true
            }
            R.id.actionDelete -> {
                navigate().toConfirmation(
                    stringRes(R.string.dialog_confirmation_title),
                    stringRes(R.string.dialog_remove_challenge_message)
                ) {
                    dispatch(ChallengeAction.Remove(challengeId))
                    router.handleBack()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    private fun showEdit() {
        navigateFromRoot().toEditChallenge(challengeId)
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        showBackButton()
        val showTitle =
            appBarOffsetListener.currentState != AppBarStateChangeListener.State.EXPANDED
        (activity as MainActivity).supportActionBar?.setDisplayShowTitleEnabled(showTitle)
    }

    override fun onDetach(view: View) {
        (activity as MainActivity).supportActionBar?.setDisplayShowTitleEnabled(true)
        super.onDetach(view)
    }

    override fun onDestroyView(view: View) {
        view.appbar.removeOnOffsetChangedListener(appBarOffsetListener)
        super.onDestroyView(view)
    }

    override fun render(state: ChallengeViewState, view: View) {
        when (state.type) {
            ChallengeViewState.StateType.DATA_CHANGED -> {
                showComplete = state.canComplete
                showEdit = state.canEdit
                activity!!.invalidateOptionsMenu()
                if (state.canAdd) {
                    view.addQuests.visible()
                } else {
                    view.addQuests.gone()
                }

                colorLayout(state, view)

                renderName(state.name, view)

                view.difficulty.text = state.difficulty

                view.endDate.text = state.endText

                view.nextDate.text = state.nextText

                renderTags(state.tags, view)
                renderTrackedValues(state.trackedValueViewModels, view)
                renderMotivations(state, view)
                renderNote(state, view)
                renderQuests(state, view)
                renderHabits(state, view)
            }

            else -> {
            }
        }
    }

    private fun renderTrackedValues(progress: List<TrackedValueViewModel>, view: View) {
        (view.trackedValueList.adapter as TrackedValueAdapter).updateAll(progress)
    }

    private fun renderTags(
        tags: List<Tag>,
        view: View
    ) {
        view.tagList.removeAllViews()

        val inflater = LayoutInflater.from(activity!!)
        tags.forEach { tag ->
            val item = inflater.inflate(R.layout.item_quest_tag_list, view.tagList, false)
            renderTag(item, tag)
            view.tagList.addView(item)
        }
    }

    private fun renderTag(view: View, tag: Tag) {
        view.tagName.text = tag.name
        val indicator = view.tagName.compoundDrawablesRelative[0] as GradientDrawable
        indicator.setColor(colorRes(tag.color.androidColor.color500))
    }

    private fun renderNote(
        state: ChallengeViewState,
        view: View
    ) {
        if (state.note != null && state.note.isNotBlank()) {
            view.note.setMarkdown(state.note)
        } else {
            view.note.setText(R.string.tap_to_add_note)
            view.note.setTextColor(colorRes(colorTextSecondaryResource))
        }
        view.note.onDebounceClick { showEdit() }
    }

    private fun renderMotivations(state: ChallengeViewState, view: View) {
        val motivationsViews = listOf(view.motivation1, view.motivation2, view.motivation3)
        motivationsViews.forEach { it.gone() }
        state.motivations.forEachIndexed { index, text ->
            val mView = motivationsViews[index]
            mView.visible()
            @SuppressLint("SetTextI18n")
            mView.text = "${index + 1}. $text"
        }
    }

    private fun renderQuests(state: ChallengeViewState, view: View) {
        if (state.questViewModels.isEmpty()) {
            view.emptyQuestList.visible()
            view.questList.gone()
        } else {
            (view.questList.adapter as QuestAdapter).updateAll(state.questViewModels)
            view.emptyQuestList.gone()
            view.questList.visible()
        }
    }

    private fun renderHabits(state: ChallengeViewState, view: View) {
        if (state.habitViewModels.isEmpty()) {
            view.emptyHabitList.visible()
            view.habitList.gone()
        } else {
            (view.habitList.adapter as HabitAdapter).updateAll(state.habitViewModels)
            view.emptyHabitList.gone()
            view.habitList.visible()
        }
    }

    private fun colorLayout(
        state: ChallengeViewState,
        view: View
    ) {
        view.appbar.setBackgroundColor(colorRes(state.color500))
        view.toolbar.setBackgroundColor(colorRes(state.color500))
        view.collapsingToolbarContainer.setContentScrimColor(colorRes(state.color500))
        activity?.window?.navigationBarColor = colorRes(state.color500)
        activity?.window?.statusBarColor = colorRes(state.color700)
    }

    private fun renderName(
        name: String,
        view: View
    ) {
        toolbarTitle = name
        view.name.text = name
    }

    sealed class TrackedValueViewModel(
        override val id: String
    ) : RecyclerViewViewModel {

        data class Progress(
            override val id: String,
            val progressPercent: Int,
            val progressText: String,
            val currentDate: LocalDate,
            val calendars: List<Calendar>,
            val monthTitle: String
        ) : TrackedValueViewModel(id)

        data class Target(
            override val id: String,
            val name: String,
            val units: String,
            val isCumulative: Boolean,
            val currentValue: String,
            val targetValue: Double,
            val progressPercent: Int,
            val remainingText: String,
            val chartMin: Double,
            val chartMax: Double,
            val chartDates: List<LocalDate>,
            val chartEntries: List<Entry>
        ) : TrackedValueViewModel(id)

        data class Average(
            override val id: String,
            val name: String,
            val units: String,
            val averageValue: Double,
            val targetValue: Double,
            val deviationText: String,
            val chartMax: Double,
            val chartDates: List<LocalDate>,
            val chartEntries: List<BarEntry>,
            val chartEntryColors: List<Int>
        ) : TrackedValueViewModel(id)
    }

    enum class ViewType {
        TARGET, AVERAGE, PROGRESS
    }

    inner class TrackedValueAdapter : MultiViewRecyclerViewAdapter<TrackedValueViewModel>() {
        override fun onRegisterItemBinders() {

            registerBinder<TrackedValueViewModel.Progress>(
                ViewType.PROGRESS.ordinal,
                R.layout.item_challenge_progress
            ) { vm, view, _ ->
                @SuppressLint("SetTextI18n")
                view.progressCompleteCurrent.text = "${vm.progressPercent}%"
                view.progressCompleteProgress.progress = vm.progressPercent
                view.progressCompleteRemaining.text = vm.progressText

                view.calendarView.clearSchemeDate()
                view.calendarView.setOnMonthChangeListener(null)
                view.calendarView.post {

                    view.calendarView.setSchemeDate(vm.calendars.map { it.toString() to it }.toMap())
                    view.calendarMonth.text = vm.monthTitle
                    view.calendarView.scrollToCalendar(
                        vm.currentDate.year,
                        vm.currentDate.monthValue,
                        vm.currentDate.dayOfMonth
                    )
                    view.calendarView.setOnMonthChangeListener(SkipFirstChangeMonthListener { year, month ->
                        dispatch(
                            ChallengeAction.ChangeProgressMonth(
                                challengeId,
                                YearMonth.of(year, month)
                            )
                        )
                    })
                }
            }

            registerBinder<TrackedValueViewModel.Target>(
                ViewType.TARGET.ordinal,
                R.layout.item_challenge_target_value
            ) { vm, view, _ ->
                @SuppressLint("SetTextI18n")
                view.targetTrackedValueLabel.text = "${vm.name} (${vm.units})"
                view.targetTrackedValueProgress.progress = vm.progressPercent
                view.targetTrackedValueCurrent.text = vm.currentValue
                view.targetTrackedValueRemaining.text = vm.remainingText
                view.targetTrackedValueLog.onDebounceClick {
                    navigate().toLogValue(
                        valueName = vm.name,
                        valueUnits = vm.units,
                        showAccumulateValueHint = vm.isCumulative,
                        logValueListener = { log ->
                            dispatch(
                                ChallengeAction.LogValue(
                                    challengeId = challengeId,
                                    trackValueId = vm.id,
                                    log = log
                                )
                            )
                        })
                }
                setupLineChart(view.targetTrackedValueChart)
                renderTargetValueChart(vm, view.targetTrackedValueChart)
            }

            registerBinder<TrackedValueViewModel.Average>(
                ViewType.AVERAGE.ordinal,
                R.layout.item_challenge_average_value
            ) { vm, view, _ ->
                @SuppressLint("SetTextI18n")
                view.averageValueLabel.text = "${vm.name} (${vm.units})"
                view.logAverageValue.onDebounceClick {
                    navigate().toLogValue(
                        valueName = vm.name,
                        valueUnits = vm.units,
                        showAccumulateValueHint = false,
                        logValueListener = { log ->
                            dispatch(
                                ChallengeAction.LogValue(
                                    challengeId = challengeId,
                                    trackValueId = vm.id,
                                    log = log
                                )
                            )
                        })
                }
                view.averageValue.text = DECIMAL_FORMATTER.format(vm.averageValue)
                view.averageValueDeviation.text = vm.deviationText
                setupBarChart(view.averageValueChart)
                renderAverageValueChart(vm, view.averageValueChart)
            }
        }

        inner class SkipFirstChangeMonthListener(private inline val onChange: (Int, Int) -> Unit) :
            CalendarView.OnMonthChangeListener {

            override fun onMonthChange(year: Int, month: Int) {
                if (isFirstChange) {
                    isFirstChange = false
                    return
                }

                onChange(year, month)
            }

            private var isFirstChange = true
        }
    }

    private fun renderAverageValueChart(
        trackedValue: TrackedValueViewModel.Average,
        chart: CombinedChart
    ) {

        val barDataSet = BarDataSet(
            trackedValue.chartEntries,
            trackedValue.name
        )
        barDataSet.colors = trackedValue.chartEntryColors
        barDataSet.axisDependency = YAxis.AxisDependency.RIGHT
        val data = BarData(barDataSet)
        data.setValueTextSize(ViewUtils.spToPx(4f, activity!!).toFloat())
        data.setValueTextColor(colorRes(colorTextPrimaryResource))
        data.setValueFormatter { value, _, _, _ ->
            DECIMAL_FORMATTER.format(value)
        }

        data.barWidth = 0.5f
        data.isHighlightEnabled = false

        chart.xAxis.valueFormatter = IndexAxisValueFormatter(
            trackedValue.chartDates.map { DateFormatter.formatWithoutYear(chart.context, it) }
        )

        val goalSet = LineDataSet(
            listOf(
                Entry(
                    -1f,
                    trackedValue.targetValue.toFloat()
                )
            ) + trackedValue.chartDates.mapIndexed { index, _ ->
                Entry(index.toFloat(), trackedValue.targetValue.toFloat())
            } + listOf(
                Entry(
                    trackedValue.chartDates.size.toFloat(),
                    trackedValue.targetValue.toFloat()
                )
            ), "goal"
        )

        goalSet.isHighlightEnabled = false
        goalSet.color = attrData(R.attr.colorAccent)
        goalSet.lineWidth = ViewUtils.dpToPx(0.5f, chart.context)
        goalSet.setDrawCircles(false)

        goalSet.mode = LineDataSet.Mode.LINEAR
        goalSet.setDrawValues(false)
        goalSet.axisDependency = YAxis.AxisDependency.RIGHT

        val combinedData = CombinedData()
        combinedData.setData(LineData(goalSet))
        combinedData.setData(data)

        chart.data = combinedData

        chart.xAxis.axisMinimum = -0.5f
        chart.xAxis.axisMaximum = trackedValue.chartDates.size.toFloat() - 0.5f

        chart.axisRight.axisMinimum = 0f
        chart.axisRight.axisMaximum = trackedValue.chartMax.toFloat()
        chart.setVisibleYRange(0f, trackedValue.chartMax.toFloat(), YAxis.AxisDependency.RIGHT)

        chart.invalidate()
        chart.animateY(longAnimTime.toInt(), AccelerateDecelerateEasingFunction)
    }

    private fun renderTargetValueChart(
        trackedValue: TrackedValueViewModel.Target,
        chart: LineChart
    ) {
        chart.marker = AwesomenessScoreMarker(chart.context)

        val logDataSet = LineDataSet(trackedValue.chartEntries, trackedValue.name)

        logDataSet.color = attrData(R.attr.colorPrimary)
        logDataSet.lineWidth = ViewUtils.dpToPx(1f, activity!!)
        logDataSet.setDrawCircles(true)

        logDataSet.circleRadius = 6f
        logDataSet.setCircleColor(attrData(R.attr.colorPrimary))
        logDataSet.setDrawCircleHole(false)
        logDataSet.highLightColor = attrData(R.attr.colorAccent)

        logDataSet.mode = LineDataSet.Mode.LINEAR
        logDataSet.setDrawValues(false)
        logDataSet.axisDependency = YAxis.AxisDependency.RIGHT

        val goalSet = LineDataSet(
            trackedValue.chartDates.mapIndexed { index, _ ->
                Entry(index.toFloat(), trackedValue.targetValue.toFloat())
            }, "goal"
        )

        goalSet.isHighlightEnabled = false
        goalSet.color = attrData(R.attr.colorAccent)
        goalSet.lineWidth = ViewUtils.dpToPx(0.5f, chart.context)
        goalSet.setDrawCircles(false)

        goalSet.mode = LineDataSet.Mode.LINEAR
        goalSet.setDrawValues(false)
        goalSet.axisDependency = YAxis.AxisDependency.RIGHT

        chart.xAxis.valueFormatter = IndexAxisValueFormatter(
            trackedValue.chartDates.map { DateFormatter.formatWithoutYear(chart.context, it) }
        )

        chart.data = LineData(goalSet, logDataSet)

        chart.setVisibleYRange(
            trackedValue.chartMin.toFloat(),
            trackedValue.chartMax.toFloat(),
            YAxis.AxisDependency.RIGHT
        )

        chart.axisRight.axisMinimum = trackedValue.chartMin.toFloat()
        chart.axisRight.axisMaximum = trackedValue.chartMax.toFloat()

        chart.invalidate()
        chart.animateX(longAnimTime.toInt(), AccelerateDecelerateEasingFunction)
    }

    data class QuestViewModel(
        override val id: String,
        val name: String,
        @ColorRes val color: Int,
        @ColorRes val textColor: Int,
        val icon: IIcon,
        val isRepeating: Boolean,
        val isCompleted: Boolean
    ) : RecyclerViewViewModel

    inner class QuestAdapter :
        BaseRecyclerViewAdapter<QuestViewModel>(R.layout.item_challenge_quest) {

        override fun onBindViewModel(vm: QuestViewModel, view: View, holder: SimpleViewHolder) {
            view.questName.text = vm.name
            view.questName.setTextColor(colorRes(vm.textColor))

            view.questIcon.backgroundTintList =
                ColorStateList.valueOf(colorRes(vm.color))
            view.questIcon.setImageDrawable(
                IconicsDrawable(view.context)
                    .icon(vm.icon)
                    .colorRes(R.color.md_white)
                    .sizeDp(22)
            )
            view.questRepeatIndicator.visible = vm.isRepeating

            view.onDebounceClick {
                if (vm.isRepeating) {
                    navigateFromRoot().toRepeatingQuest(vm.id, HorizontalChangeHandler())
                } else {
                    navigateFromRoot().toQuest(vm.id, HorizontalChangeHandler())
                }
            }
        }
    }

    data class HabitViewModel(
        override val id: String,
        val name: String,
        @ColorRes val color: Int,
        val icon: IIcon
    ) : RecyclerViewViewModel

    inner class HabitAdapter :
        BaseRecyclerViewAdapter<HabitViewModel>(R.layout.item_challenge_quest) {
        override fun onBindViewModel(vm: HabitViewModel, view: View, holder: SimpleViewHolder) {
            view.questName.text = vm.name

            view.questIcon.backgroundTintList =
                ColorStateList.valueOf(colorRes(vm.color))
            view.questIcon.setImageDrawable(
                IconicsDrawable(view.context)
                    .icon(vm.icon)
                    .colorRes(R.color.md_white)
                    .sizeDp(22)
            )

            view.questRepeatIndicator.gone()

            view.onDebounceClick {
                navigateFromRoot().toEditHabit(vm.id, HorizontalChangeHandler())
            }
        }

    }

    private val ChallengeViewState.color500
        get() = color.androidColor.color500

    private val ChallengeViewState.color700
        get() = color.androidColor.color700

    private val ChallengeViewState.endText
        get() = DateFormatter.formatWithoutYear(activity!!, endDate)

    private val ChallengeViewState.nextText
        get() = nextDate?.let { DateFormatter.formatWithoutYear(activity!!, it) }
            ?: stringRes(R.string.unscheduled)

    private val ChallengeViewState.trackedValueViewModels
        get() = trackedValues.sortedWith(Comparator { t1, t2 ->
            when {
                t1 is Challenge.TrackedValue.Progress -> -1
                t2 is Challenge.TrackedValue.Progress -> 1
                else -> 0
            }
        }).map {
            when (it) {
                is Challenge.TrackedValue.Progress -> {
                    val today = LocalDate.now()
                    TrackedValueViewModel.Progress(
                        id = it.id,
                        progressPercent = ((it.completedCount.toFloat() / it.allCount.toFloat()) * 100).toInt(),
                        progressText = if (it.allCount == 0)
                            "No Quests added"
                        else
                            "${it.completedCount}/${it.allCount} Quests done",
                        currentDate = currentDate,
                        calendars = progressItems!!.map { c ->
                            val itemDate = c.date

                            Calendar().apply {
                                day = itemDate.dayOfMonth
                                month = itemDate.monthValue
                                year = itemDate.year
                                isCurrentDay = itemDate == today
                                isCurrentMonth = itemDate.month == today.month
                                isLeapYear = itemDate.isLeapYear
                                scheme = "${c.state.name},${c.color.name},${c.progress}," +
                                    "${c.shouldDoNothing}," +
                                    "${c.isPreviousCompleted},${c.isNextCompleted}," +
                                    "${c.isFirstDay},${c.isEndDay}"
                            }
                        },
                        monthTitle = "${
                        currentDate.month.getDisplayName(
                            TextStyle.FULL,
                            Locale.getDefault()
                        )}, ${currentDate.year}"
                    )
                }

                is Challenge.TrackedValue.Target -> {

                    val dates = LocalDate.now().minusDays(9).datesBetween(LocalDate.now())

                    val valueHistory = if (it.isCumulative) it.cumulativeHistory!! else it.history

                    val values = dates.map { d -> valueHistory[d]?.value }

                    val minVal =
                        if (it.isCumulative)
                            0.0
                        else
                            values.filterNotNull().min() ?: Math.min(
                                it.startValue,
                                it.targetValue
                            )

                    val maxVal =
                        if (it.isCumulative)
                            Math.max(it.targetValue, values.filterNotNull().max() ?: 0.0)
                        else
                            values.filterNotNull().max() ?: Math.max(
                                it.startValue,
                                it.targetValue
                            )

                    val chartEntries = dates.mapIndexed { index, localDate ->
                        valueHistory[localDate]?.let { log ->
                            Entry(
                                index.toFloat(),
                                log.value.toFloat(),
                                log.value.toFloat()
                            )
                        }
                    }.filterNotNull()

                    val total = Math.abs(it.targetValue - it.startValue)
                    val complete = Math.abs(it.currentValue - it.startValue)
                    val progressPercent = ((complete / total) * 100.0).toInt()

                    TrackedValueViewModel.Target(
                        id = it.id,
                        name = it.name,
                        units = it.units,
                        isCumulative = it.isCumulative,
                        currentValue = Constants.DECIMAL_FORMATTER.format(it.currentValue),
                        targetValue = it.targetValue,
                        progressPercent = progressPercent,
                        remainingText = "${DECIMAL_FORMATTER.format(it.remainingValue)} ${it.units} remaining",
                        chartMin = Math.max(Math.floor((minVal - minVal * 0.1) / 10) * 10, 0.0),
                        chartMax = Math.ceil((maxVal + maxVal * 0.1) / 10) * 10,
                        chartDates = dates,
                        chartEntries = if (chartEntries.isEmpty()) {
                            listOf(
                                Entry(
                                    (dates.size - 1).toFloat(),
                                    it.currentValue.toFloat(),
                                    it.currentValue.toFloat()
                                )
                            )
                        } else {
                            chartEntries
                        }
                    )
                }

                is Challenge.TrackedValue.Average -> {
                    val dates = LocalDate.now().minusDays(6).datesBetween(LocalDate.now())

                    val values = dates.map { d -> it.history[d]?.value }

                    val nonNullValues = values.filterNotNull()

                    val valueSum = nonNullValues.sum()
                    val average =
                        if (nonNullValues.isEmpty()) it.targetValue else valueSum / nonNullValues.size
                    val deviation = Math.abs(it.targetValue - average)
                    val deviationText = if (average >= it.lowerBound && average <= it.upperBound) {
                        "You're doing great!"
                    } else if (average <= it.lowerBound) {
                        "${DECIMAL_FORMATTER.format(deviation)} below target"
                    } else {
                        "${DECIMAL_FORMATTER.format(deviation)} above target"
                    }

                    val maxVal = Math.max(nonNullValues.max() ?: 0.0, it.upperBound)

                    val chartEntries = values.asSequence().mapIndexed { index, v ->
                        if (v != null) {
                            BarEntry(index.toFloat(), v.toFloat())
                        } else null
                    }.filterNotNull().toMutableList()

                    val chartColors = nonNullValues.map { v ->
                        if (v >= it.lowerBound && v <= it.upperBound) {
                            colorRes(R.color.md_green_500)
                        } else
                            colorRes(R.color.md_red_500)
                    }

                    TrackedValueViewModel.Average(
                        id = it.id,
                        name = it.name,
                        units = it.units,
                        averageValue = average,
                        targetValue = it.targetValue,
                        deviationText = deviationText,
                        chartMax = Math.ceil((maxVal + maxVal * 0.1) / 10) * 10,
                        chartDates = dates,
                        chartEntries = chartEntries,
                        chartEntryColors = chartColors
                    )
                }
            }
        }

    private val ChallengeViewState.questViewModels
        get() = quests.map {
            when (it) {
                is Quest -> QuestViewModel(
                    id = it.id,
                    name = it.name,
                    color = if (it.isCompleted) R.color.md_grey_300 else it.color.androidColor.color500,
                    textColor = if (it.isCompleted) colorTextHintResource else colorTextPrimaryResource,
                    icon = it.icon?.androidIcon?.icon ?: GoogleMaterial.Icon.gmd_local_florist,
                    isRepeating = false,
                    isCompleted = it.isCompleted
                )
                is RepeatingQuest -> QuestViewModel(
                    id = it.id,
                    name = it.name,
                    color = it.color.androidColor.color500,
                    textColor = if (it.isCompleted) colorTextHintResource else colorTextPrimaryResource,
                    icon = it.icon?.androidIcon?.icon ?: GoogleMaterial.Icon.gmd_local_florist,
                    isRepeating = true,
                    isCompleted = it.isCompleted
                )
            }
        }

    private val ChallengeViewState.habitViewModels
        get() = habits.map {
            HabitViewModel(
                id = it.id,
                name = it.name,
                color = it.color.androidColor.color500,
                icon = it.icon.androidIcon.icon
            )
        }
}