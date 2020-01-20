package io.ipoli.android.quest

import io.ipoli.android.common.AppState
import io.ipoli.android.common.BaseViewStateReducer
import io.ipoli.android.common.DataLoadedAction
import io.ipoli.android.common.datetime.Duration
import io.ipoli.android.common.datetime.Minute
import io.ipoli.android.common.datetime.Time
import io.ipoli.android.common.datetime.minutes

import io.ipoli.android.common.redux.Action
import io.ipoli.android.common.redux.BaseViewState
import io.ipoli.android.common.view.AndroidColor
import io.ipoli.android.common.view.AndroidIcon
import io.ipoli.android.pet.Food
import io.ipoli.android.quest.CompletedQuestViewState.StateType.*
import io.ipoli.android.tag.Tag
import org.threeten.bp.LocalDate

/**
 * Created by Polina Zhelyazkova <polina@mypoli.fun>
 * on 1/24/18.
 */

sealed class CompletedQuestAction : Action {
    data class Load(val questId: String) : CompletedQuestAction() {
        override fun toMap() = mapOf("questId" to questId)
    }
}

object CompletedQuestReducer : BaseViewStateReducer<CompletedQuestViewState>() {

    override val stateKey = key<CompletedQuestViewState>()

    override fun reduce(
        state: AppState,
        subState: CompletedQuestViewState,
        action: Action
    ) = when (action) {

        is DataLoadedAction.QuestChanged -> {

            val quest = action.quest

            if (!quest.isCompleted) {
                subState.copy(
                    type = QUEST_UNDO_COMPLETED
                )
            } else {

                val timer = if (!quest.hasTimer) {
                    CompletedQuestViewState.Timer.Untracked
                } else if (quest.hasPomodoroTimer) {
                    val timeRanges = quest.timeRanges

                    val completedCnt = timeRanges.filter { it.end != null }.size / 2

                    val work = timeRanges.filter { it.type == TimeRange.Type.POMODORO_WORK }
                    val workDuration = work.map { it.duration }.sum()
                    val workActualDuration =
                        work.map { it.actualDuration() }.sumBy { it.asMinutes.intValue }

                    val breaks =
                        timeRanges.filter { it.type == TimeRange.Type.POMODORO_LONG_BREAK || it.type == TimeRange.Type.POMODORO_SHORT_BREAK }

                    val breakDuration = breaks.map { it.duration }.sum()

                    val breakActualDuration =
                        breaks.map { it.actualDuration() }.sumBy { it.asMinutes.intValue }

                    CompletedQuestViewState.Timer.Pomodoro(
                        completedPomodoros = completedCnt,
                        totalPomodoros = quest.totalPomodoros!!,
                        workDuration = workActualDuration.minutes,
                        overdueWorkDuration = workActualDuration.minutes - workDuration.minutes,
                        breakDuration = breakActualDuration.minutes,
                        overdueBreakDuration = breakActualDuration.minutes - breakDuration.minutes
                    )
                } else {
                    CompletedQuestViewState.Timer.Countdown(
                        quest.duration.minutes,
                        quest.actualDuration.asMinutes - quest.duration.minutes
                    )
                }

                val player = state.dataState.player!!

                val reward = quest.reward!!

                subState.copy(
                    type = DATA_LOADED,
                    name = quest.name,
                    tags = quest.tags,
                    icon = quest.icon?.let {
                        AndroidIcon.valueOf(it.name)
                    },
                    color = AndroidColor.valueOf(quest.color.name),
                    totalDuration = quest.actualDuration.asMinutes,
                    completeAt = quest.completedAtDate!!,
                    startedAt = quest.actualStartTime,
                    finishedAt = quest.completedAtTime,
                    timer = timer,
                    experience = reward.experience,
                    coins = reward.coins,
                    bounty = reward.bounty.let {
                        if (it is Quest.Bounty.Food) {
                            it.food
                        } else {
                            null
                        }
                    },
                    playerLevel = player.level,
                    playerLevelProgress = player.experienceProgressForLevel,
                    playerLevelMaxProgress = player.experienceForNextLevel
                )
            }
        }
        else -> subState
    }

    override fun defaultState() = CompletedQuestViewState(LOADING)

}

data class CompletedQuestViewState(
    val type: StateType,
    val name: String? = null,
    val tags: List<Tag> = emptyList(),
    val icon: AndroidIcon? = null,
    val color: AndroidColor? = null,
    val totalDuration: Duration<Minute>? = null,
    val completeAt: LocalDate? = null,
    val startedAt: Time? = null,
    val finishedAt: Time? = null,
    val timer: Timer? = null,
    val experience: Int? = null,
    val coins: Int? = null,
    val bounty: Food? = null,
    val playerLevel: Int? = null,
    val playerLevelProgress: Int? = null,
    val playerLevelMaxProgress: Int? = null
) : BaseViewState() {

    enum class StateType {
        LOADING,
        DATA_LOADED,
        QUEST_UNDO_COMPLETED
    }

    sealed class Timer {
        data class Pomodoro(
            val completedPomodoros: Int,
            val totalPomodoros: Int,
            val workDuration: Duration<Minute>,
            val overdueWorkDuration: Duration<Minute>,
            val breakDuration: Duration<Minute>,
            val overdueBreakDuration: Duration<Minute>
        ) : Timer()

        data class Countdown(
            val duration: Duration<Minute>,
            val overdueDuration: Duration<Minute>
        ) : Timer()

        object Untracked : Timer()
    }
}