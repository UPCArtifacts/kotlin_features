package io.ipoli.android.growth.usecase

import io.ipoli.android.common.UseCase
import io.ipoli.android.common.datetime.*
import io.ipoli.android.growth.persistence.AppUsageStat
import io.ipoli.android.growth.persistence.AppUsageStatRepository
import io.ipoli.android.planday.usecase.CalculateAwesomenessScoreUseCase
import io.ipoli.android.planday.usecase.CalculateFocusDurationUseCase
import io.ipoli.android.quest.Color
import io.ipoli.android.quest.Icon
import io.ipoli.android.quest.Quest
import io.ipoli.android.quest.data.persistence.QuestRepository
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import org.threeten.bp.temporal.TemporalAdjusters
import kotlin.math.roundToInt

class CalculateGrowthStatsUseCase(
    private val calculateAwesomenessScoreUseCase: CalculateAwesomenessScoreUseCase,
    private val calculateFocusDurationUseCase: CalculateFocusDurationUseCase,
    private val questRepository: QuestRepository,
    private val appUsageStatRepository: AppUsageStatRepository
) : UseCase<CalculateGrowthStatsUseCase.Params, CalculateGrowthStatsUseCase.Result> {

    private fun calculateAwesomenessScore(quests: List<Quest>) =
        calculateAwesomenessScoreUseCase.execute(
            CalculateAwesomenessScoreUseCase.Params.WithQuests(
                quests
            )
        )

    private fun calculateProductiveMinutes(quests: List<Quest>) =
        calculateFocusDurationUseCase.execute(
            CalculateFocusDurationUseCase.Params.WithQuests(quests)
        )

    override fun execute(parameters: Params): CalculateGrowthStatsUseCase.Result {

        val monthStart = parameters.currentDate.withDayOfMonth(1)
        val monthEnd = monthStart.with(TemporalAdjusters.lastDayOfMonth())
        val weekStartDate =
            parameters.currentDate.with(TemporalAdjusters.previousOrSame(parameters.firstDayOfWeek))
        val weekEndDate =
            parameters.currentDate.with(TemporalAdjusters.nextOrSame(parameters.lastDayOfWeek))

        val periodStart =
            monthStart.with(TemporalAdjusters.previousOrSame(parameters.firstDayOfWeek))
        val periodEnd = monthEnd.with(TemporalAdjusters.nextOrSame(parameters.lastDayOfWeek))

        val quests =
            questRepository.findOriginallyScheduledOrCompletedInPeriod(periodStart, periodEnd)

        val focusTimeByDay = quests.filter { it.isCompleted }
            .groupBy { it.completedAtDate }
            .map {
                it.key!! to calculateProductiveMinutes(it.value)
            }.toMap()

        val awesomenessScoreByDay = quests
            .filter { it.originalScheduledDate != null }
            .groupBy { it.originalScheduledDate!! }
            .map {
                it.key to calculateAwesomenessScore(it.value)
            }.toMap()

        val todayQuests = quests.filter { it.completedAtDate == parameters.currentDate }

        val weeklyQuests = quests.filter {
            it.completedAtDate?.isBetween(
                weekStartDate,
                weekEndDate
            ) ?: false
        }

        val monthlyQuests = quests.filter { it.completedAtDate != null }

        val todayUsageStats =
            if (parameters.includeAppUsageStats) appUsageStatRepository.findForDate() else emptyList()
        val weeklyUsageStats =
            if (parameters.includeAppUsageStats) appUsageStatRepository.findForWeek() else emptyList()
        val monthlyUsageStats =
            if (parameters.includeAppUsageStats) appUsageStatRepository.findForMonth() else emptyList()
        return Result(
            todayGrowth = Growth.Today(
                date = parameters.currentDate,
                stats = createSummaryStats(todayQuests),
                tagProgress = createTagProgress(todayQuests),
                challengeProgress = createTodayChallengeProgress(quests, parameters.currentDate),
                progressEntries = createTodayProgressEntries(todayQuests),
                appUsageStats = todayUsageStats,
                totalAppUsage = todayUsageStats.sumBy { it.usageDuration.intValue }.seconds
            ),
            weeklyGrowth = Growth.Week(
                startDate = weekStartDate,
                endDate = weekEndDate,
                stats = createSummaryStats(weeklyQuests),
                tagProgress = createTagProgress(weeklyQuests),
                challengeProgress = createWeeklyChallengeProgress(
                    quests = quests,
                    weekStartDate = weekStartDate,
                    weekEndDate = weekEndDate
                ),
                progressEntries = createWeeklyProgressEntries(
                    weekStartDate = weekStartDate,
                    focusTimeByDay = focusTimeByDay,
                    awesomenessScoreByDay = awesomenessScoreByDay
                ),
                appUsageStats = weeklyUsageStats,
                totalAppUsage = weeklyUsageStats.sumBy { it.usageDuration.intValue }.seconds
            ),
            monthlyGrowth = Growth.Month(
                stats = createSummaryStats(monthlyQuests),
                tagProgress = createTagProgress(monthlyQuests),
                challengeProgress = createMonthlyChallengeProgress(
                    quests = quests,
                    periodStart = periodStart,
                    periodEnd = periodEnd
                ),
                progressEntries = createMonthlyProgressEntries(
                    periodStart = periodStart,
                    periodEnd = periodEnd,
                    focusTimeByDay = focusTimeByDay,
                    awesomenessScoreByDay = awesomenessScoreByDay
                ),
                appUsageStats = monthlyUsageStats,
                totalAppUsage = monthlyUsageStats.sumBy { it.usageDuration.intValue }.seconds
            )
        )
    }

    private fun createTagProgress(todayQuests: List<Quest>): List<TagTimeSpent> {
        val questsByMainTag = todayQuests
            .filter { it.tags.isNotEmpty() }
            .groupBy { it.tags.first() }

        var totalTimeSpent = 0

        val tts = questsByMainTag
            .map { (tag, quests) ->
                val ts = timeSpentFor(quests)
                totalTimeSpent += ts.intValue
                TagTimeSpent(
                    tagId = tag.id,
                    name = tag.name,
                    color = tag.color,
                    icon = tag.icon,
                    timeSpent = ts,
                    focusTime = focusTimeFor(quests)
                )
            }.sortedByDescending {
                it.timeSpent.intValue
            }

        return tts.map {
            it.copy(
                timeSpentPercent = ((it.timeSpent.intValue / totalTimeSpent.toFloat()) * 100f)
                    .roundToInt()
            )
        }
    }

    private fun focusTimeFor(quests: List<Quest>) =
        timeSpentFor(
            quests
                .filter { it.hasTimer })

    private fun createSummaryStats(quests: List<Quest>) =
        SummaryStats(
            timeSpent = timeSpentFor(quests),
            experienceEarned = quests.map { it.reward!!.experience }.sum(),
            coinsEarned = quests.map { it.reward!!.coins }.sum()
        )

    private fun createTodayChallengeProgress(
        quests: List<Quest>,
        currentDate: LocalDate
    ): List<ChallengeProgress> {
        val todayQuests =
            quests
                .filter { (it.completedAtDate == currentDate || it.scheduledDate == currentDate) && it.challengeId != null }

        return createChallenges(todayQuests)
    }

    private fun createWeeklyChallengeProgress(
        quests: List<Quest>,
        weekStartDate: LocalDate,
        weekEndDate: LocalDate
    ): List<ChallengeProgress> {
        val weeklyQuests =
            quests.filter {

                val completed = it.completedAtDate?.isBetween(
                    weekStartDate,
                    weekEndDate
                ) ?: false

                val scheduled = it.scheduledDate?.isBetween(
                    weekStartDate,
                    weekEndDate
                ) ?: false

                (completed || scheduled) && it.challengeId != null
            }

        return createChallenges(weeklyQuests)
    }

    private fun createMonthlyChallengeProgress(
        quests: List<Quest>,
        periodStart: LocalDate,
        periodEnd: LocalDate
    ): List<ChallengeProgress> {
        val weeklyQuests =
            quests.filter {

                val completed = it.completedAtDate?.isBetween(
                    periodStart,
                    periodEnd
                ) ?: false

                val scheduled = it.scheduledDate?.isBetween(
                    periodStart,
                    periodEnd
                ) ?: false

                (completed || scheduled) && it.challengeId != null
            }

        return createChallenges(weeklyQuests)
    }

    private fun createChallenges(questsForPeriod: List<Quest>): List<ChallengeProgress> {
        val questsByChallenge = questsForPeriod.groupBy { it.challengeId!! }
        return questsByChallenge.map {
            val qs = it.value
            val completedCount = qs.count { it.isCompleted }
            ChallengeProgress(
                id = it.key,
                progressPercent = ((completedCount / qs.size.toFloat()) * 100f).toInt(),
                completeQuestCount = completedCount,
                totalQuestCount = qs.size,
                timeSpent = minutesSpentForQuests(qs)
            )
        }
    }

    private fun minutesSpentForQuests(qs: List<Quest>) =
        timeSpentFor(qs
            .filter { it.isCompleted })

    private fun timeSpentFor(quests: List<Quest>): Duration<Minute> {
        return quests
            .map { it.actualDuration.asMinutes.intValue }
            .sum()
            .minutes
    }

    private fun createTodayProgressEntries(
        todayQuests: List<Quest>
    ): List<ProgressEntry.Today> {

        val ranges = LinkedHashMap<Time, MutableList<Quest>>()
        (6..24 step 3).forEach {
            ranges[Time.atHours(it)] = mutableListOf()
        }

        todayQuests.forEach { q ->
            for (time in ranges.keys) {
                if (q.completedAtTime!! < time) {
                    ranges[time]!!.add(q)
                    break
                }
            }
        }

        var prodMins = 0
        var awesomeScore = 0.0

        return ranges.map { (t, qs) ->
            prodMins += calculateProductiveMinutes(qs).intValue
            awesomeScore += calculateAwesomenessScore(qs)
            ProgressEntry.Today(
                periodEnd = t,
                productiveMinutes = prodMins.minutes,
                awesomenessScore = awesomeScore
            )
        }
    }

    private fun createWeeklyProgressEntries(
        weekStartDate: LocalDate,
        focusTimeByDay: Map<LocalDate, Duration<Minute>>,
        awesomenessScoreByDay: Map<LocalDate, Double>
    ): List<ProgressEntry.Week> {

        val weekDays = weekStartDate.datesAhead(7)

        return weekDays.map {
            ProgressEntry.Week(
                date = it,
                productiveMinutes = focusTimeByDay[it] ?: 0.minutes,
                awesomenessScore = awesomenessScoreByDay[it] ?: 0.0
            )
        }
    }

    private fun createMonthlyProgressEntries(
        periodStart: LocalDate,
        periodEnd: LocalDate,
        focusTimeByDay: Map<LocalDate, Duration<Minute>>,
        awesomenessScoreByDay: Map<LocalDate, Double>
    ): MutableList<ProgressEntry.Month> {
        val monthProgressEntries = mutableListOf<ProgressEntry.Month>()
        var start = periodStart
        while (start.isBefore(periodEnd)) {
            val end = start.plusDays(6)

            val mins = start.datesBetween(end).map {
                focusTimeByDay[it] ?: 0.minutes
            }
            val scores = start.datesBetween(end).map {
                awesomenessScoreByDay[it] ?: 0.0
            }

            monthProgressEntries.add(
                ProgressEntry.Month(
                    weekStart = start,
                    weekEnd = end,
                    productiveMinutes = (mins.sumBy { it.intValue } / 7).minutes,
                    awesomenessScore = (scores.sum() / 7.0)
                )
            )
            start = start.plusWeeks(1)
        }
        return monthProgressEntries
    }

    data class TagTimeSpent(
        val tagId: String,
        val name: String,
        val color: Color,
        val icon: Icon?,
        val timeSpentPercent: Int = -1,
        val timeSpent: Duration<Minute>,
        val focusTime: Duration<Minute>
    )

    data class ChallengeProgress(
        val id: String,
        val progressPercent: Int,
        val completeQuestCount: Int,
        val totalQuestCount: Int,
        val timeSpent: Duration<Minute>
    )

    data class SummaryStats(
        val timeSpent: Duration<Minute>,
        val experienceEarned: Int,
        val coinsEarned: Int
    )

    sealed class ProgressEntry(
        open val productiveMinutes: Duration<Minute>,
        open val awesomenessScore: Double
    ) {

        data class Today(
            val periodEnd: Time,
            override val productiveMinutes: Duration<Minute>,
            override val awesomenessScore: Double
        ) : ProgressEntry(productiveMinutes, awesomenessScore)

        data class Week(
            val date: LocalDate,
            override val productiveMinutes: Duration<Minute>,
            override val awesomenessScore: Double
        ) : ProgressEntry(productiveMinutes, awesomenessScore)

        data class Month(
            val weekStart: LocalDate,
            val weekEnd: LocalDate,
            override val productiveMinutes: Duration<Minute>,
            override val awesomenessScore: Double
        ) : ProgressEntry(productiveMinutes, awesomenessScore)
    }

    sealed class Growth {
        data class Today(
            val date: LocalDate,
            val stats: SummaryStats,
            val tagProgress: List<TagTimeSpent>,
            val challengeProgress: List<ChallengeProgress>,
            val progressEntries: List<ProgressEntry.Today>,
            val appUsageStats: List<AppUsageStat>,
            val totalAppUsage: Duration<Second>
        ) : Growth()

        data class Week(
            val startDate: LocalDate,
            val endDate: LocalDate,
            val stats: SummaryStats,
            val tagProgress: List<TagTimeSpent>,
            val challengeProgress: List<ChallengeProgress>,
            val progressEntries: List<ProgressEntry.Week>,
            val appUsageStats: List<AppUsageStat>,
            val totalAppUsage: Duration<Second>
        ) : Growth()

        data class Month(
            val stats: SummaryStats,
            val tagProgress: List<TagTimeSpent>,
            val challengeProgress: List<ChallengeProgress>,
            val progressEntries: List<ProgressEntry.Month>,
            val appUsageStats: List<AppUsageStat>,
            val totalAppUsage: Duration<Second>
        ) : Growth()
    }

    data class Params(
        val includeAppUsageStats: Boolean = false,
        val currentDate: LocalDate = LocalDate.now(),
        val firstDayOfWeek: DayOfWeek = DateUtils.firstDayOfWeek,
        val lastDayOfWeek: DayOfWeek = DateUtils.lastDayOfWeek
    )

    data class Result(
        val todayGrowth: Growth.Today,
        val weeklyGrowth: Growth.Week,
        val monthlyGrowth: Growth.Month
    )
}