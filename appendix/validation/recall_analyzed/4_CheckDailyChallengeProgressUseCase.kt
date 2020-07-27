package io.ipoli.android.dailychallenge.usecase

import io.ipoli.android.common.UseCase
import io.ipoli.android.dailychallenge.data.persistence.DailyChallengeRepository
import io.ipoli.android.quest.data.persistence.QuestRepository
import org.threeten.bp.LocalDate

open class CheckDailyChallengeProgressUseCase(
    private val dailyChallengeRepository: DailyChallengeRepository,
    private val questRepository: QuestRepository
) : UseCase<Unit, CheckDailyChallengeProgressUseCase.Result> {

    override fun execute(parameters: Unit): Result {
        val dc = dailyChallengeRepository.findForDate(LocalDate.now()) //#inference
            ?: return Result.NotScheduledForToday 

        if (dc.isCompleted) {
            return Result.Complete
        }

        val qs = questRepository.findQuestsForDailyChallenge(dc) //#inference

        if(qs.isEmpty()) {
            return Result.Inactive
        }

        val completeCount = qs.count { it.isCompleted } //#inference,lambda
        return if (completeCount == 3) {
            Result.Complete
        } else
            Result.Incomplete(completeCount)
    }

    sealed class Result { //#sealed_class
        object NotScheduledForToday : Result()
        object Inactive : Result()
        data class Incomplete(val completeQuestCount: Int) : Result() //#data_class
        object Complete : Result()
    }
}
