package io.ipoli.android.common.datetime

import org.threeten.bp.*
import org.threeten.bp.format.TextStyle
import org.threeten.bp.temporal.ChronoUnit
import java.util.*

/**
 * Created by Venelin Valkov <venelin@mypoli.fun>
 * on 8/20/17.
 */

val LocalDate.isToday get() = LocalDate.now().isEqual(this)

val LocalDate.isTomorrow get() = LocalDate.now().plusDays(1).isEqual(this)

val LocalDate.isYesterday get() = LocalDate.now().minusDays(1).isEqual(this)

val LocalDate.dayOfWeekText: String
    get() = dayOfWeek.getDisplayName(
        TextStyle.FULL,
        Locale.getDefault()
    )

fun LocalDate.startOfDayUTC() = toStartOfDayUTC().time

fun LocalDate.startOfDay() = toStartOfDay().time

fun LocalDate.toStartOfDayUTC() = fromZonedDateTime(this.atStartOfDay(DateUtils.ZONE_UTC))

fun LocalDate.toStartOfDay() = fromZonedDateTime(this.atStartOfDay(ZoneId.systemDefault()))

fun LocalDate.fromZonedDateTime(dateTime: ZonedDateTime) = Date(dateTime.toInstant().toEpochMilli())

fun LocalDate.isBetween(start: LocalDate?, end: LocalDate?): Boolean {
    return if (start == null || end == null) {
        false
    } else !isBefore(start) && !isAfter(end)
}

fun LocalDate.isBeforeOrEqual(date: LocalDate) = !isAfter(date)
fun LocalDate.isAfterOrEqual(date: LocalDate) = !isBefore(date)

fun LocalDate.isNotEqual(otherDate: LocalDate) = !isEqual(otherDate)

fun LocalDate.datesBetween(date: LocalDate): List<LocalDate> {
    val days = ChronoUnit.DAYS.between(this, date)
    return (0..days).map { this.plusDays(it) }
}

fun LocalDate.datesAhead(count: Int): List<LocalDate> {
    return 0.until(count).map { this.plusDays(it.toLong()) }
}

fun LocalDate.daysBetween(date: LocalDate) =
    daysUntil(date.plusDays(1))

fun LocalDate.daysUntil(date: LocalDate) =
    this.until(date, ChronoUnit.DAYS)

fun LocalDate.weeksUntil(date: LocalDate) =
    this.until(date, ChronoUnit.WEEKS)

val LocalDate.weekOfMonth get() = get(DateUtils.weekOfMonth)

val LocalDate.weekOfYear get() = get(DateUtils.weekOfYear)

fun LocalDate.toLocalDateTime(time: Time = Time.now()): LocalDateTime =
    LocalDateTime.of(this, time.toLocalTime())

fun LocalDateTime.toTime(): Time =
    Time.at(this.hour, this.minute)

fun LocalDateTime.toMillis(zoneId: ZoneId) = atZone(zoneId).toInstant().toEpochMilli()

fun LocalDateTime.toMillis() = atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()

operator fun Instant.plus(other: Instant): Instant {
    return Instant.ofEpochMilli(toEpochMilli() + other.toEpochMilli())
}

operator fun Instant.minus(other: Instant): Instant {
    return Instant.ofEpochMilli(toEpochMilli() - other.toEpochMilli())
}

fun Instant.plusMinutes(minutes: Long): Instant = plus(minutes, ChronoUnit.MINUTES)

val Instant.milliseconds get() = toEpochMilli().milliseconds

val Long.instant: Instant get() = Instant.ofEpochMilli(this)

val Long.startOfDayUTC: LocalDate get() = DateUtils.fromMillisUTC(this)