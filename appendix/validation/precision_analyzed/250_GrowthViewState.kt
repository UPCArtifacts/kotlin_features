package io.ipoli.android.growth

import io.ipoli.android.Constants
import io.ipoli.android.challenge.entity.Challenge
import io.ipoli.android.common.AppState
import io.ipoli.android.common.BaseViewStateReducer
import io.ipoli.android.common.DataLoadedAction
import io.ipoli.android.common.datetime.*

import io.ipoli.android.common.redux.Action
import io.ipoli.android.common.redux.BaseViewState
import io.ipoli.android.growth.persistence.AppUsageStat
import io.ipoli.android.growth.usecase.CalculateGrowthStatsUseCase
import io.ipoli.android.quest.Color
import org.threeten.bp.LocalDate
import org.threeten.bp.temporal.TemporalAdjusters

/**
 * Created by Venelin Valkov <venelin@mypoli.fun>
 * on 05/23/2018.
 */

sealed class GrowthAction : Action {
    object Load : GrowthAction()
    object ShowToday : GrowthAction()
    object ShowWeek : GrowthAction()
    object ShowMonth : GrowthAction()
}

object GrowthReducer : BaseViewStateReducer<GrowthViewState>() {

    override fun reduce(
        state: AppState,
        subState: GrowthViewState,
        action: Action
    ) =
        when (action) {

            is DataLoadedAction.GrowthChanged -> {

                val growth = action.dailyGrowth
                val newState = subState.copy(
                    timeSpent = growth.stats.timeSpent,
                    experience = growth.stats.experienceEarned,
                    coins = growth.stats.coinsEarned,
                    showProgressCount = progressCountForToday(),
                    tagTimeSpent = growth.tagProgress,
                    progressEntries = growth.progressEntries,
                    appUsageStats = growth.appUsageStats,
                    totalAppUsage = growth.totalAppUsage,
                    hasAppUsageStatsPermission = action.includesAppUsageData,
                    todayGrowth = growth,
                    weekGrowth = action.weeklyGrowth,
                    monthGrowth = action.monthlyGrowth
                )

                if (state.dataState.challenges == null) {
                    newState.copy(type = GrowthViewState.StateType.LOADING)
                } else {
                    newState.copy(
                        type = GrowthViewState.StateType.TODAY_DATA_LOADED,
                        challengeProgress = createChallengeProgress(
                            growth.challengeProgress,
                            state.dataState.challenges
                        )
                    )
                }
            }

            is DataLoadedAction.ChallengesChanged -> {
                when (subState.type) {

                    GrowthViewState.StateType.LOADING -> {
                        if (subState.todayGrowth != null) {
                            subState.copy(
                                type = GrowthViewState.StateType.TODAY_DATA_LOADED,
                                challengeProgress = createChallengeProgress(
                                    subState.todayGrowth.challengeProgress,
                                    action.challenges
                                )
                            )
                        } else {
                            subState
                        }
                    }

                    GrowthViewState.StateType.TODAY_DATA_LOADED ->
                        subState.copy(
                            challengeProgress = createChallengeProgress(
                                subState.todayGrowth!!.challengeProgress,
                                action.challenges
                            )
                        )

                    GrowthViewState.StateType.WEEK_DATA_LOADED ->
                        subState.copy(
                            challengeProgress = createChallengeProgress(
                                subState.weekGrowth!!.challengeProgress,
                                action.challenges
                            )
                        )

                    GrowthViewState.StateType.MONTH_DATA_LOADED ->
                        subState.copy(
                            challengeProgress = createChallengeProgress(
                                subState.monthGrowth!!.challengeProgress,
                                action.challenges
                            )
                        )
                }

            }

            is GrowthAction.ShowToday -> {
                val growth = subState.todayGrowth
                subState.copy(
                    type = GrowthViewState.StateType.TODAY_DATA_LOADED,
                    timeSpent = growth!!.stats.timeSpent,
                    experience = growth.stats.experienceEarned,
                    coins = growth.stats.coinsEarned,
                    showProgressCount = progressCountForToday(),
                    tagTimeSpent = growth.tagProgress,
                    challengeProgress = createChallengeProgress(
                        growth.challengeProgress,
                        state.dataState.challenges!!
                    ),
                    progressEntries = growth.progressEntries,
                    appUsageStats = growth.appUsageStats,
                    totalAppUsage = growth.totalAppUsage
                )
            }

            is GrowthAction.ShowWeek -> {
                val growth = subState.weekGrowth
                subState.copy(
                    type = GrowthViewState.StateType.WEEK_DATA_LOADED,
                    timeSpent = growth!!.stats.timeSpent,
                    experience = growth.stats.experienceEarned,
                    coins = growth.stats.coinsEarned,
                    showProgressCount = progressCountForThisWeek(),
                    tagTimeSpent = growth.tagProgress,
                    challengeProgress = createChallengeProgress(
                        growth.challengeProgress,
                        state.dataState.challenges!!
                    ),
                    progressEntries = growth.progressEntries,
                    appUsageStats = growth.appUsageStats,
                    totalAppUsage = growth.totalAppUsage
                )
            }

            is GrowthAction.ShowMonth -> {
                val growth = subState.monthGrowth
                subState.copy(
                    type = GrowthViewState.StateType.MONTH_DATA_LOADED,
                    timeSpent = growth!!.stats.timeSpent,
                    experience = growth.stats.experienceEarned,
                    coins = growth.stats.coinsEarned,
                    showProgressCount = progressCountForThisMonth(growth.progressEntries),
                    tagTimeSpent = growth.tagProgress,
                    challengeProgress = createChallengeProgress(
                        growth.challengeProgress,
                        state.dataState.challenges!!
                    ),
                    progressEntries = growth.progressEntries,
                    appUsageStats = growth.appUsageStats,
                    totalAppUsage = growth.totalAppUsage
                )
            }

            else -> subState
        }

    private fun progressCountForThisMonth(progressEntries: List<CalculateGrowthStatsUseCase.ProgressEntry.Month>): Int {
        val today = LocalDate.now()
        return progressEntries.indexOfFirst { today.isBetween(it.weekStart, it.weekEnd) } + 1
    }

    private fun progressCountForThisWeek(): Int {
        val today = LocalDate.now()
        return Math.max(
            today.with(TemporalAdjusters.previousOrSame(DateUtils.firstDayOfWeek))
                .daysBetween(today).toInt(), 1
        )
    }

    private fun progressCountForToday() =
        if (Time.now() <= Time.atHours(6)) 1 else ((Time.now().hours) / 3)

    private fun createChallengeProgress(
        challengeProgress: List<CalculateGrowthStatsUseCase.ChallengeProgress>,
        challenges: List<Challenge>
    ) =
        challengeProgress.map {
            val c = challenges.first { dc -> dc.id == it.id }
            GrowthViewState.ChallengeProgress(
                challengeId = it.id,
                name = c.name,
                color = c.color,
                progressPercent = it.progressPercent,
                completeQuestCount = it.completeQuestCount,
                totalQuestCount = it.totalQuestCount,
                timeSpent = it.timeSpent
            )
        }

    override fun defaultState() = GrowthViewState(
        type = GrowthViewState.StateType.LOADING,
        timeSpent = 0.minutes,
        experience = 0,
        coins = 0,
        showProgressCount = 0,
        tagTimeSpent = emptyList(),
        challengeProgress = emptyList(),
        progressEntries = emptyList(),
        appUsageStats = emptyList(),
        totalAppUsage = 0.seconds,
        hasAppUsageStatsPermission = false,
        todayGrowth = null,
        weekGrowth = null,
        monthGrowth = null
    )

    override val stateKey = key<GrowthViewState>()

}

data class GrowthViewState(
    val type: StateType,
    val timeSpent: Duration<Minute>,
    val experience: Int,
    val coins: Int,
    val productiveHoursGoal: Int = Constants.DAILY_FOCUS_HOURS_GOAL,
    val tagTimeSpent: List<CalculateGrowthStatsUseCase.TagTimeSpent>,
    val challengeProgress: List<ChallengeProgress>,
    val showProgressCount: Int,
    val progressEntries: List<CalculateGrowthStatsUseCase.ProgressEntry>,
    val appUsageStats: List<AppUsageStat>,
    val totalAppUsage: Duration<Second>,
    val hasAppUsageStatsPermission: Boolean,
    val todayGrowth: CalculateGrowthStatsUseCase.Growth.Today?,
    val weekGrowth: CalculateGrowthStatsUseCase.Growth.Week?,
    val monthGrowth: CalculateGrowthStatsUseCase.Growth.Month?
) : BaseViewState() {
    enum class StateType { LOADING, TODAY_DATA_LOADED, WEEK_DATA_LOADED, MONTH_DATA_LOADED }

    data class ChallengeProgress(
        val challengeId: String,
        val name: String,
        val color: Color,
        val progressPercent: Int,
        val completeQuestCount: Int,
        val totalQuestCount: Int,
        val timeSpent: Duration<Minute>
    )
}