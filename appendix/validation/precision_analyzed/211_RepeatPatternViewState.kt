package io.ipoli.android.repeatingquest.edit.picker

import android.support.annotation.DrawableRes
import io.ipoli.android.common.AppState
import io.ipoli.android.common.BaseViewStateReducer

import io.ipoli.android.common.redux.Action
import io.ipoli.android.common.redux.BaseViewState
import io.ipoli.android.pet.AndroidPetAvatar
import io.ipoli.android.repeatingquest.edit.picker.RepeatPatternViewState.StateType.*
import io.ipoli.android.repeatingquest.entity.RepeatPattern
import io.ipoli.android.repeatingquest.entity.RepeatType
import io.ipoli.android.repeatingquest.entity.repeatType
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate

/**
 * Created by Polina Zhelyazkova <polina@mypoli.fun>
 * on 2/21/18.
 */
sealed class RepeatPatternAction : Action {
    data class LoadData(val repeatPattern: RepeatPattern?) : RepeatPatternAction() {
        override fun toMap() = mapOf("repeatPattern" to repeatPattern)
    }

    data class ChangeFrequency(val index: Int) : RepeatPatternAction() {
        override fun toMap() = mapOf("index" to index)
    }

    data class ToggleWeekDay(val weekDay: DayOfWeek) : RepeatPatternAction() {
        override fun toMap() = mapOf("weekDay" to weekDay.name)
    }

    data class ChangeWeekDayCount(val index: Int) : RepeatPatternAction() {
        override fun toMap() = mapOf("index" to index)
    }

    data class ToggleMonthDay(val day: Int) : RepeatPatternAction() {
        override fun toMap() = mapOf("day" to day)
    }

    data class ChangeMonthDayCount(val index: Int) : RepeatPatternAction() {
        override fun toMap() = mapOf("index" to index)
    }

    data class ChangeDayOfYear(val date: LocalDate) : RepeatPatternAction() {
        override fun toMap() = mapOf("date" to date)
    }

    data class ChangeStartDate(val date: LocalDate) : RepeatPatternAction() {
        override fun toMap() = mapOf("date" to date)
    }

    data class ChangeEndDate(val date: LocalDate) : RepeatPatternAction() {
        override fun toMap() = mapOf("date" to date)
    }

    data class ChangeEveryXDaysCount(val index: Int) : RepeatPatternAction() {
        override fun toMap() = mapOf("index" to index)
    }

    object CreatePattern : RepeatPatternAction()
}


object RepeatPatternReducer : BaseViewStateReducer<RepeatPatternViewState>() {

    override val stateKey = key<RepeatPatternViewState>()

    override fun reduce(
        state: AppState,
        subState: RepeatPatternViewState,
        action: Action
    ): RepeatPatternViewState {
        return when (action) {
            is RepeatPatternAction.LoadData -> {
                val pattern = action.repeatPattern
                val repeatType = pattern?.repeatType ?: defaultState().repeatType

                val selectedWeekDays =
                    if (pattern != null) selectedWeekDaysFor(pattern) else subState.selectedWeekDays

                val selectedDaysPerWeek = pattern?.let {
                    if (it is RepeatPattern.Weekly || it is RepeatPattern.Flexible.Weekly)
                        it.periodCount
                    else
                        defaultState().weekDaysCount
                } ?: defaultState().monthDaysCount

                val weekDaysCountIndex =
                    subState.weekCountValues.indexOfFirst { it == selectedDaysPerWeek }

                val selectedMonthDays =
                    if (pattern != null) selectedMonthDaysFor(pattern) else subState.selectedMonthDays

                val selectedDaysPerMonth = pattern?.let {
                    if (it is RepeatPattern.Monthly || it is RepeatPattern.Flexible.Monthly)
                        it.periodCount
                    else
                        defaultState().monthDaysCount
                } ?: defaultState().monthDaysCount

                val monthDaysCountIndex =
                    subState.monthCountValues.indexOfFirst { it == selectedDaysPerMonth }

                val startDate = pattern?.startDate ?: subState.startDate
                val endDate = pattern?.endDate ?: subState.endDate

                val petAvatar = AndroidPetAvatar.valueOf(state.dataState.player!!.pet.avatar.name)

                val isFlexible =
                    pattern?.let { it is RepeatPattern.Flexible } ?: defaultState().isFlexible

                val everyXDaysCountIndex = pattern?.let {
                    if (it is RepeatPattern.EveryXDays) {
                        defaultState().everyXDaysValues.indexOfFirst { v -> v == it.xDays }
                    } else defaultState().everyXDaysCountIndex
                } ?: defaultState().everyXDaysCountIndex

                subState.copy(
                    type = DATA_LOADED,
                    repeatType = repeatType,
                    repeatTypeIndex = repeatTypeIndexFor(repeatType),
                    weekDaysCountIndex = weekDaysCountIndex,
                    monthDaysCountIndex = monthDaysCountIndex,
                    everyXDaysCountIndex = everyXDaysCountIndex,
                    selectedWeekDays = selectedWeekDays,
                    selectedMonthDays = selectedMonthDays,
                    isFlexible = isFlexible,
                    startDate = startDate,
                    endDate = endDate,
                    pickerEndDate = endDate ?: subState.pickerEndDate,
                    petAvatar = petAvatar.headImage
                )
            }

            is RepeatPatternAction.ChangeFrequency -> {
                val repeatType = repeatTypeForIndex(action.index)
                subState.copy(
                    type = REPEAT_TYPE_CHANGED,
                    repeatType = repeatType,
                    repeatTypeIndex = repeatTypeIndexFor(repeatType),
                    isFlexible = isFlexible(repeatType, subState)
                )
            }

            is RepeatPatternAction.ToggleWeekDay -> {
                val weekDay = action.weekDay
                val selectedWeekDays = if (subState.selectedWeekDays.contains(weekDay)) {
                    subState.selectedWeekDays.minus(weekDay)
                } else {
                    subState.selectedWeekDays.plus(weekDay)
                }

                subState.copy(
                    type = WEEK_DAYS_CHANGED,
                    selectedWeekDays = selectedWeekDays,
                    isFlexible = subState.weekDaysCount != selectedWeekDays.size
                )
            }

            is RepeatPatternAction.ChangeWeekDayCount -> {
                subState.copy(
                    type = COUNT_CHANGED,
                    weekDaysCountIndex = action.index,
                    isFlexible = subState.weekCountValues[action.index] != subState.selectedWeekDays.size
                )
            }

            is RepeatPatternAction.ToggleMonthDay -> {
                val day = action.day
                val selectedMonthDays = if (subState.selectedMonthDays.contains(day)) {
                    subState.selectedMonthDays.minus(day)
                } else {
                    subState.selectedMonthDays.plus(day)
                }
                subState.copy(
                    type = MONTH_DAYS_CHANGED,
                    selectedMonthDays = selectedMonthDays,
                    isFlexible = subState.monthDaysCount != selectedMonthDays.size
                )
            }

            is RepeatPatternAction.ChangeMonthDayCount -> {
                subState.copy(
                    type = COUNT_CHANGED,
                    monthDaysCountIndex = action.index,
                    isFlexible = subState.monthCountValues[action.index] != subState.selectedMonthDays.size
                )
            }

            is RepeatPatternAction.ChangeEveryXDaysCount -> {
                subState.copy(
                    type = EVERY_X_DAYS_CHANGED,
                    everyXDaysCountIndex = action.index
                )
            }

            is RepeatPatternAction.ChangeDayOfYear -> {
                subState.copy(
                    type = YEAR_DAY_CHANGED,
                    dayOfYear = action.date
                )
            }

            is RepeatPatternAction.ChangeStartDate -> {
                subState.copy(
                    type = START_DATE_CHANGED,
                    startDate = action.date,
                    pickerEndDate = if (subState.endDate == null)
                        action.date.plusDays(1)
                    else subState.pickerEndDate
                )
            }

            is RepeatPatternAction.ChangeEndDate -> {
                subState.copy(
                    type = END_DATE_CHANGED,
                    endDate = action.date,
                    pickerEndDate = action.date
                )
            }

            RepeatPatternAction.CreatePattern -> {
                subState.copy(
                    type = PATTERN_CREATED,
                    resultPattern = createRepeatPattern(subState)
                )
            }

            else -> {
                subState
            }
        }
    }

    private fun createRepeatPattern(state: RepeatPatternViewState): RepeatPattern =
        when (state.repeatType) {
            RepeatType.DAILY -> {
                RepeatPattern.Daily(state.startDate, state.endDate)
            }
            RepeatType.WEEKLY -> {
                when {
                    state.selectedWeekDays.size == 7 -> RepeatPattern.Daily(
                        startDate = state.startDate,
                        endDate = state.endDate
                    )
                    state.isFlexible -> RepeatPattern.Flexible.Weekly(
                        timesPerWeek = state.weekDaysCount,
                        preferredDays = state.selectedWeekDays,
                        startDate = state.startDate,
                        endDate = state.endDate
                    )
                    else -> RepeatPattern.Weekly(
                        daysOfWeek = state.selectedWeekDays,
                        startDate = state.startDate,
                        endDate = state.endDate
                    )
                }
            }
            RepeatType.MONTHLY -> {
                if (state.isFlexible) {
                    RepeatPattern.Flexible.Monthly(
                        timesPerMonth = state.monthDaysCount,
                        preferredDays = state.selectedMonthDays,
                        startDate = state.startDate,
                        endDate = state.endDate
                    )
                } else {
                    RepeatPattern.Monthly(
                        daysOfMonth = state.selectedMonthDays,
                        startDate = state.startDate,
                        endDate = state.endDate
                    )
                }
            }
            RepeatType.YEARLY -> {
                val date = state.dayOfYear
                RepeatPattern.Yearly(
                    dayOfMonth = date.dayOfMonth,
                    month = date.month,
                    startDate = state.startDate,
                    endDate = state.endDate
                )
            }
            RepeatType.EVERY_X_DAYS -> {
                RepeatPattern.EveryXDays(
                    xDays = state.everyXDaysValues[state.everyXDaysCountIndex],
                    startDate = state.startDate,
                    endDate = state.endDate
                )
            }

        }

    private fun isFlexible(
        repeatType: RepeatType,
        state: RepeatPatternViewState
    ) =
        when (repeatType) {
            RepeatType.DAILY -> false
            RepeatType.YEARLY -> false
            RepeatType.EVERY_X_DAYS -> false
            RepeatType.WEEKLY -> {
                val daysCount = state.weekCountValues[state.weekDaysCountIndex]
                daysCount != state.selectedWeekDays.size
            }
            RepeatType.MONTHLY -> {
                val daysCount = state.monthCountValues[state.monthDaysCountIndex]
                daysCount != state.selectedMonthDays.size
            }
        }

    private fun repeatTypeForIndex(index: Int) =
        RepeatType.values()[index]

    private fun repeatTypeIndexFor(repeatType: RepeatType) =
        repeatType.ordinal

    private fun selectedWeekDaysFor(pattern: RepeatPattern): Set<DayOfWeek> =
        when (pattern) {
            is RepeatPattern.Weekly -> pattern.daysOfWeek
            is RepeatPattern.Flexible.Weekly -> pattern.preferredDays
            else -> setOf()
        }

    private fun selectedMonthDaysFor(pattern: RepeatPattern): Set<Int> =
        when (pattern) {
            is RepeatPattern.Monthly -> pattern.daysOfMonth
            is RepeatPattern.Flexible.Monthly -> pattern.preferredDays
            else -> setOf()
        }

    override fun defaultState() =
        RepeatPatternViewState(
            LOADING,
            RepeatType.WEEKLY,
            repeatTypeIndex = repeatTypeIndexFor(RepeatType.WEEKLY),
            weekDaysCountIndex = 0,
            monthDaysCountIndex = 0,
            everyXDaysCountIndex = 0,
            selectedWeekDays = setOf(),
            selectedMonthDays = setOf(),
            weekCountValues = (1..6).toList(),
            monthCountValues = (1..31).toList(),
            everyXDaysValues = (2..7).toList(),
            isFlexible = true,
            dayOfYear = LocalDate.now(),
            startDate = LocalDate.now(),
            endDate = null,
            pickerEndDate = LocalDate.now(),
            resultPattern = null,
            petAvatar = null
        )

}

data class RepeatPatternViewState(
    val type: RepeatPatternViewState.StateType,
    val repeatType: RepeatType,
    val repeatTypeIndex: Int,
    val weekDaysCountIndex: Int,
    val monthDaysCountIndex: Int,
    val everyXDaysCountIndex: Int,
    val selectedWeekDays: Set<DayOfWeek>,
    val selectedMonthDays: Set<Int>,
    val weekCountValues: List<Int>,
    val monthCountValues: List<Int>,
    val everyXDaysValues: List<Int>,
    val isFlexible: Boolean,
    val dayOfYear: LocalDate,
    val startDate: LocalDate,
    val endDate: LocalDate?,
    val pickerEndDate: LocalDate,
    val resultPattern: RepeatPattern?,
    @DrawableRes val petAvatar: Int?
) : BaseViewState() {
    enum class StateType {
        LOADING,
        DATA_LOADED,
        REPEAT_TYPE_CHANGED,
        WEEK_DAYS_CHANGED,
        COUNT_CHANGED,
        MONTH_DAYS_CHANGED,
        YEAR_DAY_CHANGED,
        START_DATE_CHANGED,
        END_DATE_CHANGED,
        PATTERN_CREATED,
        EVERY_X_DAYS_CHANGED
    }

    val weekDaysCount
        get() = weekCountValues[weekDaysCountIndex]

    val monthDaysCount
        get() = monthCountValues[monthDaysCountIndex]
}