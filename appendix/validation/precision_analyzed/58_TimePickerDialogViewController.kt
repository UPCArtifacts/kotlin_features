package io.ipoli.android.common

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.TimePicker
import io.ipoli.android.R
import io.ipoli.android.common.datetime.Time

import io.ipoli.android.common.redux.Action
import io.ipoli.android.common.redux.BaseViewState
import io.ipoli.android.common.view.BaseDialogController

/**
 * Created by Polina Zhelyazkova <polina@mypoli.fun>
 * on 6/1/18.
 */
sealed class TimePickerDialogAction : Action

object TimePickerDialogReducer : BaseViewStateReducer<TimePickerDialogViewState>() {
    override val stateKey = key<TimePickerDialogViewState>()

    override fun reduce(
        state: AppState,
        subState: TimePickerDialogViewState,
        action: Action
    ): TimePickerDialogViewState {
        return subState
    }

    override fun defaultState() = TimePickerDialogViewState(
        type = TimePickerDialogViewState.StateType.LOADING
    )

}

data class TimePickerDialogViewState(
    val type: StateType
) : BaseViewState() {
    enum class StateType {
        LOADING
    }
}

@Suppress("DEPRECATION")
class TimePickerDialogViewController(args: Bundle? = null) :
    BaseDialogController(args) {

    private var time: Time? = null
    private var shouldUse24HourFormat: Boolean = false
    private lateinit var listener: (Time?) -> Unit
    private var showNeutral: Boolean = true
    private var onDismissListener: (() -> Unit)? = null

    constructor(
        time: Time? = null,
        shouldUse24HourFormat: Boolean,
        showNeutral: Boolean = false,
        listener: (Time?) -> Unit
    ) : this() {
        this.time = time
        this.showNeutral = showNeutral
        this.shouldUse24HourFormat = shouldUse24HourFormat
        this.listener = listener
    }

    @SuppressLint("InflateParams")
    override fun onCreateContentView(inflater: LayoutInflater, savedViewState: Bundle?): View {
        val view = inflater.inflate(R.layout.dialog_time_picker, null)
        (view as TimePicker).setIs24HourView(shouldUse24HourFormat)
        time?.let {
            view.currentHour = it.hours
            view.currentMinute = it.getMinutes()
        }
        return view
    }

    override fun onCreateDialog(
        dialogBuilder: AlertDialog.Builder,
        contentView: View,
        savedViewState: Bundle?
    ): AlertDialog {
        val builder = dialogBuilder
            .setCustomTitle(null)
            .setPositiveButton(R.string.dialog_ok) { _, _ ->
                listener(
                    Time.at(
                        (contentView as TimePicker).currentHour,
                        contentView.currentMinute
                    )
                )
            }
            .setNegativeButton(R.string.cancel, null)
        if (showNeutral) {
            builder.setNeutralButton(R.string.do_not_know) { _, _ ->
                listener(null)
            }
        }
        return builder.create()
    }

    override fun createHeaderView(inflater: LayoutInflater): View? = null

    fun setOnDismissListener(onDismiss: () -> Unit) {
        this.onDismissListener = onDismiss
    }

    override fun onDismiss() {
        onDismissListener?.invoke()
    }
}