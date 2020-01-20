package io.ipoli.android.quest.reminder.picker

import io.ipoli.android.Constants
import io.ipoli.android.common.AppState
import io.ipoli.android.common.BaseViewStateReducer

import io.ipoli.android.common.parser.ReminderMinutesParser
import io.ipoli.android.common.redux.Action
import io.ipoli.android.common.redux.BaseViewState
import io.ipoli.android.pet.PetAvatar
import io.ipoli.android.quest.reminder.picker.ReminderPickerViewState.StateType.*

/**
 * Created by Venelin Valkov <venelin@mypoli.fun>
 * on 10/5/17.
 */

sealed class ReminderPickerAction : Action {
    data class Load(val reminder: ReminderViewModel?) : ReminderPickerAction() {
        override fun toMap() = mapOf("reminder" to reminder)
    }

    data class ChangeMessage(val message: String) : ReminderPickerAction() {
        override fun toMap() = mapOf("message" to message)
    }

    data class ChangeCustomTime(val timeValue: String) : ReminderPickerAction() {
        override fun toMap() = mapOf("timeValue" to timeValue)
    }
    data class ChangePredefinedTime(val index: Int) :
        ReminderPickerAction() {
        override fun toMap() = mapOf("index" to index)
    }

    data class ChangeTimeUnit(val index: Int) : ReminderPickerAction() {
        override fun toMap() = mapOf("index" to index)
    }
    object PickReminder : ReminderPickerAction()
}

object ReminderPickerReducer : BaseViewStateReducer<ReminderPickerViewState>() {
    override fun reduce(
        state: AppState,
        subState: ReminderPickerViewState,
        action: Action
    ) =
        when (action) {

            is ReminderPickerAction.Load -> {
                val petAvatar = state.dataState.player!!.pet.avatar
                if (action.reminder == null) {
                    loadNewReminderData(subState.copy(petAvatar = petAvatar))
                } else {
                    loadExistingReminderData(action.reminder, subState.copy(petAvatar = petAvatar))
                }
            }

            is ReminderPickerAction.PickReminder ->
                if (subState.timeUnitIndex != null && subState.timeValue.isEmpty()) {
                    subState.copy(type = TIME_VALUE_VALIDATION_ERROR)
                } else {
                    subState.copy(
                        type = FINISHED,
                        viewModel = ReminderViewModel(
                            subState.message,
                            calculateMinutesFromStart(subState)
                        )
                    )
                }

            is ReminderPickerAction.ChangeMessage ->
                subState.copy(type = NEW_VALUES, message = action.message)

            is ReminderPickerAction.ChangePredefinedTime -> {
                val isCustomItemIndex = Constants.REMINDER_PREDEFINED_MINUTES.size == action.index
                if (isCustomItemIndex) {
                    subState.copy(
                        type = CUSTOM_TIME,
                        timeValue = "",
                        timeUnitIndex = 0,
                        predefinedIndex = null
                    )
                } else {
                    subState.copy(type = NEW_VALUES, predefinedIndex = action.index)
                }
            }

            is ReminderPickerAction.ChangeCustomTime ->
                subState.copy(type = NEW_VALUES, timeValue = action.timeValue)

            is ReminderPickerAction.ChangeTimeUnit ->
                subState.copy(type = NEW_VALUES, timeUnitIndex = action.index)

            else -> subState
        }

    private fun loadNewReminderData(state: ReminderPickerViewState) =
        state.copy(
            type = NEW_REMINDER,
            predefinedIndex = 0
        )

    private fun loadExistingReminderData(
        reminder: ReminderViewModel,
        state: ReminderPickerViewState
    ): ReminderPickerViewState {

        if (reminder.minutesFromStart == 0L) {
            return state.copy(
                type = EDIT_REMINDER,
                message = reminder.message,
                predefinedIndex = 0
            )
        }

        val (timeValue, timeUnit) = ReminderMinutesParser
            .parseCustomMinutes(reminder.minutesFromStart)

        return state.copy(
            type = EDIT_REMINDER,
            message = reminder.message,
            timeValue = timeValue.toString(),
            timeUnitIndex = timeUnit.ordinal
        )
    }

    private fun calculateMinutesFromStart(state: ReminderPickerViewState): Long {
        return if (state.timeUnitIndex != null) {
            val timeUnitMinutes = TimeUnit.values()[state.timeUnitIndex].minutes
            state.timeValue.toLong() * timeUnitMinutes
        } else {
            Constants.REMINDER_PREDEFINED_MINUTES[state.predefinedIndex!!].toLong()
        }
    }

    override fun defaultState() =
        ReminderPickerViewState(type = LOADING)

    override val stateKey = key<ReminderPickerViewState>()

}

data class ReminderPickerViewState(
    val type: StateType,
    val message: String = "",
    val predefinedIndex: Int? = null,
    val timeValue: String = "",
    val timeUnitIndex: Int? = null,
    val petAvatar: PetAvatar? = null,
    val viewModel: ReminderViewModel? = null
) : BaseViewState() {

    enum class StateType {
        LOADING,
        NEW_REMINDER,
        EDIT_REMINDER,
        CUSTOM_TIME,
        NEW_VALUES,
        TIME_VALUE_VALIDATION_ERROR,
        FINISHED
    }
}