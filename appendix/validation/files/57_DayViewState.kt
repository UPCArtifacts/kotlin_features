package io.ipoli.android.quest.schedule.calendar.dayview.view

import io.ipoli.android.common.AppState
import io.ipoli.android.common.DataLoadedAction
import io.ipoli.android.common.NamespaceViewStateReducer
import io.ipoli.android.common.datetime.Time
import io.ipoli.android.common.redux.Action
import io.ipoli.android.common.redux.BaseViewState
import io.ipoli.android.quest.Color
import io.ipoli.android.quest.Icon
import io.ipoli.android.quest.reminder.picker.ReminderViewModel
import io.ipoli.android.quest.schedule.calendar.dayview.view.DayViewState.StateType.*
import io.ipoli.android.quest.usecase.Result
import io.ipoli.android.quest.usecase.Schedule
import io.ipoli.android.tag.Tag
import org.threeten.bp.LocalDate

/**
 * Created by Venelin Valkov <venelin@mypoli.fun>
 * on 10/4/17.
 */

sealed class DayViewAction : Action {
    data class Load(val currentDate: LocalDate) :
        DayViewAction() {
        override fun toMap() = mapOf("currentDate" to currentDate)
    }

    data class StartEditScheduledQuest(val questViewModel: DayViewController.ScheduledEventViewModel.Quest) :
        DayViewAction() {
        override fun toMap() = mapOf("questViewModel" to questViewModel)
    }

    data class CompleteQuest(val questId: String, val isStarted: Boolean) : DayViewAction() {
        override fun toMap() = mapOf("questId" to questId, "isStarted" to isStarted)
    }

    data class UndoCompleteQuest(val questId: String) : DayViewAction() {
        override fun toMap() = mapOf("questId" to questId)
    }

    data class AddNewScheduledQuest(val startTime: Time, val duration: Int) : DayViewAction() {
        override fun toMap() = mapOf("startTime" to startTime, "duration" to duration)
    }

    data class DragResizeView(val startTime: Time?, val endTime: Time?, val duration: Int) :
        DayViewAction() {
        override fun toMap() = mapOf(
            "startTime" to startTime, "endTime" to endTime, "duration" to duration
        )
    }

    data class DragMoveView(val startTime: Time?, val endTime: Time?) : DayViewAction() {
        override fun toMap() = mapOf("startTime" to startTime, "endTime" to endTime)
    }

    object AddQuest : DayViewAction()
    object QuestSaved : DayViewAction()
    object SaveInvalidQuestName : DayViewAction()
    data class SaveInvalidQuest(val result: Result.Invalid) : DayViewAction() {
        override fun toMap() = mapOf("result" to result.error.name)
    }

    data class ChangeEditViewName(val name: String) : DayViewAction() {
        override fun toMap() = mapOf("name" to name)
    }

    object EditQuest : DayViewAction()
    object EditUnscheduledQuest : DayViewAction()
    data class DatePicked(val date: LocalDate?) : DayViewAction() {
        override fun toMap() = mapOf("date" to date)
    }

    data class ReminderPicked(val reminder: ReminderViewModel?) : DayViewAction() {
        override fun toMap() = mapOf("reminder" to reminder)
    }

    data class IconPicked(val icon: Icon?) : DayViewAction() {
        override fun toMap() = mapOf("icon" to icon?.name)
    }

    data class ColorPicked(val color: Color) : DayViewAction() {
        override fun toMap() = mapOf("color" to color.name)
    }

    data class TagsPicked(val tags: Set<Tag>) : DayViewAction() {
        override fun toMap() = mapOf("tags" to tags.joinToString(",") { it.name })
    }

    data class StartEditUnscheduledQuest(val questViewModel: DayViewController.UnscheduledQuestViewModel) :
        DayViewAction() {
        override fun toMap() = mapOf("questViewModel" to questViewModel)
    }

    data class RemoveQuest(val questId: String) : DayViewAction() {
        override fun toMap() = mapOf("questId" to questId)
    }

    data class UndoRemoveQuest(val questId: String) : DayViewAction() {
        override fun toMap() = mapOf("questId" to questId)
    }
}

class DayViewReducer(namespace: String) : NamespaceViewStateReducer<DayViewState>(namespace) {

    override val stateKey = namespace + "/" + key<DayViewState>()

    override fun doReduce(state: AppState, subState: DayViewState, action: Action): DayViewState {
        if (action is DayViewAction) {
            return reduceDayViewAction(state, subState, action)
        }

        return when (action) {
            is DataLoadedAction.CalendarScheduleChanged -> {
                val scheduleData = action.schedule

                if (!scheduleData.containsKey(subState.currentDate)) {
                    subState.copy(
                        type = LOADING
                    )
                } else {
                    val schedule = scheduleData[subState.currentDate]!!
                    subState.copy(
                        type = SCHEDULE_LOADED,
                        schedule = schedule
                    )
                }
            }

            else -> subState
        }

    }

    private fun reduceDayViewAction(
        state: AppState,
        subState: DayViewState,
        action: DayViewAction
    ): DayViewState {
        return when (action) {
            is DayViewAction.Load -> {
                val scheduleData = state.dataState.calendarSchedule
                if (!scheduleData.containsKey(action.currentDate)) {
                    subState.copy(
                        type = LOADING,
                        currentDate = action.currentDate
                    )
                } else {
                    val schedule = scheduleData[action.currentDate]!!
                    subState.copy(
                        type = SCHEDULE_LOADED,
                        schedule = schedule,
                        currentDate = action.currentDate
                    )
                }

            }

            is DayViewAction.StartEditScheduledQuest -> {
                val vm = action.questViewModel
                subState.copy(
                    type = START_EDIT_SCHEDULED_QUEST,
                    editId = vm.id,
                    name = vm.name,
                    color = Color.valueOf(vm.backgroundColor.name),
                    startTime = Time.of(vm.startMinute),
                    duration = vm.duration,
                    endTime = Time.plusMinutes(Time.of(vm.startMinute), vm.duration),
                    icon = vm.icon?.let {
                        Icon.valueOf(it.name)
                    },
                    reminder = vm.reminder,
                    repeatingQuestId = vm.repeatingQuestId,
                    challengeId = vm.challengeId,
                    tags = vm.tags.map { it.tag }
                )
            }

            is DayViewAction.StartEditUnscheduledQuest -> {
                val vm = action.questViewModel
                subState.copy(
                    type = START_EDIT_UNSCHEDULED_QUEST,
                    editId = vm.id,
                    name = vm.name,
                    color = Color.valueOf(vm.backgroundColor.name),
                    duration = vm.duration,
                    icon = vm.icon?.let {
                        Icon.valueOf(it.name)
                    },
                    reminder = vm.reminder,
                    startTime = null,
                    repeatingQuestId = vm.repeatingQuestId,
                    challengeId = vm.challengeId,
                    tags = vm.tags.map { it.tag }
                )
            }

            is DayViewAction.CompleteQuest -> {
                subState.copy(
                    type = QUEST_COMPLETED
                )
            }

            is DayViewAction.UndoCompleteQuest -> {
                subState.copy(
                    type = UNDO_QUEST_COMPLETED
                )
            }

            is DayViewAction.AddNewScheduledQuest -> {
                subState.copy(
                    type = ADD_NEW_SCHEDULED_QUEST,
                    editId = "",
                    name = "",
                    color = Color.GREEN,
                    icon = null,
                    startTime = action.startTime,
                    duration = action.duration,
                    endTime = Time.plusMinutes(action.startTime, action.duration),
                    tags = emptyList()
                )
            }

            is DayViewAction.DragResizeView -> {
                subState.copy(
                    type = EDIT_VIEW_DRAGGED,
                    startTime = action.startTime,
                    endTime = action.endTime,
                    duration = action.duration
                )
            }

            is DayViewAction.DragMoveView -> {
                subState.copy(
                    type = EDIT_VIEW_DRAGGED,
                    startTime = action.startTime,
                    endTime = action.endTime
                )
            }

            DayViewAction.QuestSaved -> {
                subState.copy(type = EVENT_UPDATED, reminder = null, scheduledDate = null)
            }

            DayViewAction.SaveInvalidQuestName -> {
                subState.copy(type = EVENT_VALIDATION_EMPTY_NAME)
            }

            is DayViewAction.SaveInvalidQuest -> {
                when (action.result.error) {
                    Result.ValidationError.TIMER_RUNNING -> {
                        subState.copy(type = EVENT_VALIDATION_TIMER_RUNNING)
                    }
                }
            }

            is DayViewAction.ChangeEditViewName -> {
                subState.copy(
                    type = EDIT_VIEW_NAME_CHANGED,
                    name = action.name
                )
            }

            is DayViewAction.DatePicked -> {
                subState.copy(
                    type = DATE_PICKED,
                    scheduledDate = action.date
                )
            }

            is DayViewAction.ReminderPicked -> {
                subState.copy(
                    type = REMINDER_PICKED,
                    reminder = action.reminder
                )
            }

            is DayViewAction.IconPicked -> {
                subState.copy(
                    type = ICON_PICKED,
                    icon = action.icon
                )
            }

            is DayViewAction.ColorPicked -> {
                subState.copy(
                    type = COLOR_PICKED,
                    color = action.color
                )
            }

            is DayViewAction.TagsPicked -> {
                val tags = action.tags
                subState.copy(
                    type = TAGS_PICKED,
                    tags = tags.toList(),
                    color = if (tags.isNotEmpty()) tags.first().color else subState.color,
                    icon = if (tags.isNotEmpty() && subState.icon == null) tags.first().icon else subState.icon
                )
            }

            is DayViewAction.RemoveQuest -> {
                val eventId = action.questId
                if (eventId.isEmpty()) {
                    subState.copy(
                        type = NEW_EVENT_REMOVED
                    )
                } else {
                    subState.copy(
                        type = DayViewState.StateType.EVENT_REMOVED,
                        removedEventId = eventId,
                        reminder = null
                    )
                }
            }

            is DayViewAction.UndoRemoveQuest -> {
                subState.copy(type = DayViewState.StateType.UNDO_REMOVED_EVENT, removedEventId = "")
            }

            else -> {
                subState
            }
        }
    }

    override fun defaultState() =
        DayViewState(
            type = LOADING,
            currentDate = LocalDate.now(),
            schedule = null,
            removedEventId = "",
            editId = "",
            name = "",
            scheduledDate = null,
            startTime = null,
            endTime = null,
            duration = null,
            color = null,
            reminder = null,
            icon = null,
            repeatingQuestId = null,
            challengeId = null,
            tags = emptyList()
        )
}

data class DayViewState(
    val type: StateType,
    val currentDate: LocalDate,
    val schedule: Schedule?,
    val removedEventId: String,
    val editId: String,
    val name: String,
    val scheduledDate: LocalDate?,
    val startTime: Time?,
    val endTime: Time?,
    val duration: Int?,
    val color: Color?,
    val reminder: ReminderViewModel?,
    val icon: Icon?,
    val repeatingQuestId: String?,
    val challengeId: String?,
    val tags: List<Tag>
) : BaseViewState() {

    enum class StateType {
        LOADING,
        SCHEDULE_LOADED,
        ADD_NEW_SCHEDULED_QUEST,
        START_EDIT_SCHEDULED_QUEST,
        START_EDIT_UNSCHEDULED_QUEST,
        ICON_PICKED,
        COLOR_PICKED,
        EDIT_QUEST,
        EVENT_UPDATED,
        EVENT_VALIDATION_EMPTY_NAME,
        EVENT_VALIDATION_TIMER_RUNNING,
        NEW_EVENT_REMOVED,
        EVENT_REMOVED,
        UNDO_REMOVED_EVENT,
        QUEST_COMPLETED,
        UNDO_QUEST_COMPLETED,
        EDIT_VIEW_DRAGGED,
        EDIT_VIEW_NAME_CHANGED,
        REMINDER_PICKED,
        TAGS_PICKED,
        DATE_PICKED
    }
}