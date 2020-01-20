package io.ipoli.android.challenge.edit

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import io.ipoli.android.R
import io.ipoli.android.challenge.edit.LogValueViewState.StateType.*
import io.ipoli.android.challenge.entity.Challenge
import io.ipoli.android.common.AppState
import io.ipoli.android.common.BaseViewStateReducer
import io.ipoli.android.common.DataLoadedAction
import io.ipoli.android.common.Validator
import io.ipoli.android.common.datetime.Time
import io.ipoli.android.common.datetime.startOfDay
import io.ipoli.android.common.redux.Action
import io.ipoli.android.common.redux.BaseViewState
import io.ipoli.android.common.text.DateFormatter
import io.ipoli.android.common.view.ReduxDialogController
import io.ipoli.android.common.view.gone
import io.ipoli.android.common.view.visible
import io.ipoli.android.pet.AndroidPetAvatar
import io.ipoli.android.pet.PetAvatar
import kotlinx.android.synthetic.main.dialog_log_value.view.*
import kotlinx.android.synthetic.main.view_dialog_header.view.*
import org.threeten.bp.LocalDate

sealed class LogValueAction : Action {
    data class Select(val value: String, val date: LocalDate) : LogValueAction()
    data class Load(val date: LocalDate) : LogValueAction()
    data class ChangeDate(val date: LocalDate) : LogValueAction()
}

object LogValueReducer : BaseViewStateReducer<LogValueViewState>() {

    override val stateKey = key<LogValueViewState>()

    override fun reduce(
        state: AppState,
        subState: LogValueViewState,
        action: Action
    ) = when (action) {

        is LogValueAction.Load -> {
            state.dataState.player?.let {
                subState.copy(
                    type = DATA_LOADED,
                    petAvatar = it.pet.avatar,
                    date = action.date
                )
            } ?: subState.copy(
                type = LOADING,
                date = action.date
            )
        }

        is DataLoadedAction.PlayerChanged ->
            subState.copy(
                type = if (subState.type == LOADING) DATA_LOADED else PET_CHANGED,
                petAvatar = action.player.pet.avatar
            )

        is LogValueAction.ChangeDate ->
            subState.copy(
                type = DATE_CHANGED,
                date = action.date
            )

        is LogValueAction.Select -> {
            val errors = Validator.validate(action).check<ValidationError> {
                "value format" {
                    given {
                        action.value.toDoubleOrNull() == null || action.value.toDouble() < 0
                    } addError ValidationError.INCORRECT_VALUE_FORMAT
                }
            }

            if (errors.isNotEmpty()) {
                subState.copy(
                    type = VALIDATION_ERROR,
                    errors = errors.toSet()
                )
            } else {
                subState.copy(
                    type = LOG_VALUE_CHOSEN,
                    logValue = Challenge.TrackedValue.Log(
                        value = action.value.toDouble(),
                        time = Time.now(),
                        date = action.date
                    )
                )
            }
        }

        else -> subState
    }

    override fun defaultState() =
        LogValueViewState(
            type = LOADING,
            petAvatar = null,
            logValue = null,
            date = null,
            errors = emptySet()
        )

    enum class ValidationError {
        INCORRECT_VALUE_FORMAT
    }
}

data class LogValueViewState(
    val type: StateType,
    val petAvatar: PetAvatar?,
    val logValue: Challenge.TrackedValue.Log?,
    val date: LocalDate?,
    val errors: Set<LogValueReducer.ValidationError>
) : BaseViewState() {
    enum class StateType {
        LOADING, DATA_LOADED, PET_CHANGED, DATE_CHANGED, VALIDATION_ERROR, LOG_VALUE_CHOSEN
    }
}

class LogValueDialogController(args: Bundle? = null) :
    ReduxDialogController<LogValueAction, LogValueViewState, LogValueReducer>(
        args
    ) {

    private var valueName = ""
    private var valueUnits = ""
    private var showAccumulateValueHint = false

    private var logValueListener: (Challenge.TrackedValue.Log) -> Unit = {}

    private var cancelListener: () -> Unit = {}

    override val reducer = LogValueReducer

    constructor(
        valueName: String,
        valueUnits: String,
        showAccumulateValueHint: Boolean,
        logValueListener: (Challenge.TrackedValue.Log) -> Unit,
        cancelListener: () -> Unit = {}
    ) : this() {
        this.valueName = valueName
        this.valueUnits = valueUnits
        this.showAccumulateValueHint = showAccumulateValueHint
        this.logValueListener = logValueListener
        this.cancelListener = cancelListener
    }

    @SuppressLint("InflateParams", "SetTextI18n")
    override fun onCreateContentView(inflater: LayoutInflater, savedViewState: Bundle?): View {
        val v = inflater.inflate(R.layout.dialog_log_value, null)
        v.logValueHint.text = "$valueName ($valueUnits)"
        if(showAccumulateValueHint) {
            v.accumulateValueHint.visible()
        } else {
            v.accumulateValueHint.gone()
        }
        return v
    }

    override fun onCreateDialog(
        dialogBuilder: AlertDialog.Builder,
        contentView: View,
        savedViewState: Bundle?
    ): AlertDialog =
        dialogBuilder
            .setPositiveButton(R.string.record_value, null)
            .setNegativeButton(R.string.cancel, null)
            .create()

    override fun onHeaderViewCreated(headerView: View) {
        headerView.dialogHeaderTitle.setText(R.string.dialog_log_value_title)
    }

    override fun onCreateLoadAction() = LogValueAction.Load(LocalDate.now())

    override fun onDialogCreated(dialog: AlertDialog, contentView: View) {
        dialog.setOnShowListener {
            setPositiveButtonListener {
                dispatch(
                    LogValueAction.Select(
                        contentView.logValue.text.toString(),
                        contentView.logDateValue.tag as LocalDate
                    )
                )
            }

            setNeutralButtonListener {
                cancelListener()
                dismiss()
            }
        }
    }

    override fun render(state: LogValueViewState, view: View) {
        when (state.type) {

            DATA_LOADED -> {
                view.dialogContainer.requestFocus()
                state.petAvatar?.let {
                    changeIcon(AndroidPetAvatar.valueOf(it.name).headImage)
                }
                renderSelectedDate(state, view)
            }

            PET_CHANGED ->
                changeIcon(AndroidPetAvatar.valueOf(state.petAvatar!!.name).headImage)

            DATE_CHANGED ->
                renderSelectedDate(state, view)

            VALIDATION_ERROR ->
                view.logValue.error = "Set number"

            LOG_VALUE_CHOSEN -> {
                logValueListener(state.logValue!!)
                dismiss()
            }

            else -> {
            }
        }
    }

    private fun renderSelectedDate(
        state: LogValueViewState,
        view: View
    ) {
        view.logDateValue.text = DateFormatter.formatWithoutYear(view.context, state.date)
        view.logDateValue.tag = state.date!!
        view.logDateValue.onDebounceClick {

            val date = state.date
            val datePickerDialog = DatePickerDialog(
                view.context,
                DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                    dispatch(
                        LogValueAction.ChangeDate(
                            LocalDate.of(
                                year,
                                month + 1,
                                dayOfMonth
                            )
                        )
                    )
                }, date.year, date.month.value - 1, date.dayOfMonth
            )
            datePickerDialog.datePicker.maxDate = LocalDate.now().startOfDay()
            datePickerDialog.show()
        }
    }
}