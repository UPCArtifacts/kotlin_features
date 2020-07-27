package io.ipoli.android.quest.schedule.agenda.usecase

import io.ipoli.android.common.UseCase
import io.ipoli.android.quest.data.persistence.QuestRepository
import org.threeten.bp.LocalDate

/**
 * Created by Venelin Valkov <venelin@mypoli.fun>
 * on 01/29/2018.
 */
class FindAgendaDatesUseCase(private val questRepository: QuestRepository) :
    UseCase<FindAgendaDatesUseCase.Params, FindAgendaDatesUseCase.Result> {

    override fun execute(parameters: Params) =
        when (parameters) {
            is Params.Before -> executeBefore(parameters)
            is Params.After -> executeAfter(parameters)
            is Params.All -> executeAll(parameters)
        }

    private fun executeAll(parameters: Params.All): Result.All {
        require(parameters.itemsBefore > 0 && parameters.itemsAfter > 0)

        val startRes = executeBefore(Params.Before(parameters.date, parameters.itemsBefore))
        val endRes = executeAfter(Params.After(parameters.date, parameters.itemsAfter))

        return Result.All(startRes.date, endRes.date)
    }

    private fun executeAfter(parameters: Params.After): Result.After {
        require(parameters.itemCount > 0)

        val endDate = questRepository.findLastScheduledDate(
            parameters.date.minusDays(1),
            parameters.itemCount
        )

        return Result.After(endDate)
    }

    private fun executeBefore(parameters: Params.Before): Result.Before {
        require(parameters.itemCount > 0)

        val startDate = questRepository.findFirstScheduledDate(
            parameters.date,
            parameters.itemCount
        )

        return Result.Before(startDate)
    }

    sealed class Params {
        data class Before(val date: LocalDate, val itemCount: Int) : Params()
        data class After(val date: LocalDate, val itemCount: Int) : Params()
        data class All(val date: LocalDate, val itemsBefore: Int, val itemsAfter: Int) : Params()
    }

    sealed class Result {
        data class Before(val date: LocalDate?) : Result()
        data class After(val date: LocalDate?) : Result()
        data class All(val start: LocalDate?, val end: LocalDate?) : Result()
    }
}