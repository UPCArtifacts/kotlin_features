package io.ipoli.android.quest.schedule.agenda.usecase

import io.ipoli.android.common.UseCase
import io.ipoli.android.common.datetime.DateUtils
import io.ipoli.android.event.Event
import io.ipoli.android.quest.Quest
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth

/**
 * Created by Venelin Valkov <venelin@mypoli.fun>
 * on 01/29/2018.
 */
class CreateAgendaItemsUseCase :
    UseCase<CreateAgendaItemsUseCase.Params, List<CreateAgendaItemsUseCase.AgendaItem>> {

    override fun execute(parameters: Params): List<CreateAgendaItemsUseCase.AgendaItem> {

        val scheduledQuests = parameters.scheduledQuests.groupBy { it.scheduledDate!! }
        val events = parameters.events.groupBy { it.startDate }

        val beforeItems =
            createItems(
                startDate = parameters.date.minusDays(1),
                scheduledQuests = scheduledQuests,
                events = events,
                itemsToFill = parameters.itemsBefore,
                nextDate = { it.minusDays(1) },
                firstDayOfWeek = parameters.firstDayOfWeek
            )
        val afterItems =
            createItems(
                startDate = parameters.date,
                scheduledQuests = scheduledQuests,
                events = events,
                itemsToFill = parameters.itemsAfter,
                nextDate = { it.plusDays(1) },
                firstDayOfWeek = parameters.firstDayOfWeek
            )
        return beforeItems + afterItems
    }

    private fun createItems(
        startDate: LocalDate,
        scheduledQuests: Map<LocalDate, List<Quest>>,
        events: Map<LocalDate, List<Event>>,
        itemsToFill: Int,
        nextDate: (LocalDate) -> LocalDate,
        firstDayOfWeek: DayOfWeek
    ): MutableList<AgendaItem> {
        val items = mutableListOf<AgendaItem>()
        var currentDate = startDate
        while (items.size < itemsToFill) {

            val dateItems = createItemsForDate(
                currentDate = currentDate,
                firstDayOfWeek = firstDayOfWeek,
                scheduledQuests = scheduledQuests,
                events = events
            )

            val newDate = nextDate(currentDate)

            if (newDate > currentDate) items.addAll(dateItems)
            else items.addAll(0, dateItems)

            currentDate = newDate
        }

        return items
    }

    private fun createItemsForDate(
        currentDate: LocalDate,
        firstDayOfWeek: DayOfWeek,
        scheduledQuests: Map<LocalDate, List<Quest>>,
        events: Map<LocalDate, List<Event>>
    ): MutableList<AgendaItem> {
        val dateItems = mutableListOf<AgendaItem>()
        createWeekItem(currentDate, firstDayOfWeek, dateItems)
        createMonthItem(currentDate, dateItems)
        createDateAndQuestItems(scheduledQuests, events, currentDate, dateItems)
        return dateItems
    }

    private fun createDateAndQuestItems(
        scheduledQuests: Map<LocalDate, List<Quest>>,
        events: Map<LocalDate, List<Event>>,
        currentDate: LocalDate,
        dateItems: MutableList<AgendaItem>
    ) {
        if (scheduledQuests.containsKey(currentDate) || events.containsKey(currentDate)) {
            dateItems.add(AgendaItem.Date(currentDate))

            val items = mutableListOf<AgendaItem>()

            scheduledQuests[currentDate]?.forEach { items.add(AgendaItem.QuestItem(it)) }
            events[currentDate]?.forEach { items.add(AgendaItem.EventItem(it)) }

            items.sortBy {
                when (it) {
                    is AgendaItem.QuestItem ->
                        it.quest.startTime?.toMinuteOfDay()
                    is AgendaItem.EventItem ->
                        it.event.startTime.toMinuteOfDay()
                    else -> throw IllegalArgumentException("Can't sort agenda item $it")
                }
            }

            dateItems.addAll(items)
        }
    }

    private fun createMonthItem(
        currentDate: LocalDate,
        dateItems: MutableList<AgendaItem>
    ) {
        if (currentDate.dayOfMonth == 1) {
            dateItems.add(
                AgendaItem.Month(
                    YearMonth.of(
                        currentDate.year,
                        currentDate.monthValue
                    )
                )
            )
        }
    }

    private fun createWeekItem(
        currentDate: LocalDate,
        firstDayOfWeek: DayOfWeek,
        dateItems: MutableList<AgendaItem>
    ) {
        if (currentDate.dayOfWeek == firstDayOfWeek) {
            dateItems.add(AgendaItem.Week(currentDate, currentDate.plusDays(6)))
        }
    }

    data class Params(
        val date: LocalDate,
        val scheduledQuests: List<Quest>,
        val events: List<Event>,
        val itemsBefore: Int,
        val itemsAfter: Int,
        val firstDayOfWeek: DayOfWeek = DateUtils.firstDayOfWeek
    )

    sealed class AgendaItem {

        data class QuestItem(val quest: Quest) : AgendaItem() {
            override fun startDate() = quest.scheduledDate!!
        }

        data class EventItem(val event: Event) : AgendaItem() {
            override fun startDate() = event.startDate
        }

        data class Date(val date: LocalDate) : AgendaItem() {
            override fun startDate() = date
        }

        data class Week(val start: LocalDate, val end: LocalDate) : AgendaItem() {
            override fun startDate() = start
        }

        data class Month(val month: YearMonth) : AgendaItem() {
            override fun startDate(): LocalDate = LocalDate.of(month.year, month.month, 1)
        }


        abstract fun startDate(): LocalDate
    }
}