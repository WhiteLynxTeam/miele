package ru.miel.utils

import ru.miel.domain.DATE_PATTERN_DAY_ONLY
import ru.miel.domain.DATE_PATTERN_WEEK_DAY
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.time.format.TextStyle
import java.util.Date
import java.util.Locale

fun String.convertStringToLocalDate(dateFormat: String): LocalDate? {
    return try {
//        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val formatter = DateTimeFormatter.ofPattern(dateFormat)
        LocalDate.parse(this, formatter)
    } catch (e: DateTimeParseException) {
        null
    }
}

fun Long.toTextDateByFormat(dateFormat: String): String {
    val df = SimpleDateFormat(dateFormat, Locale.getDefault())
    return df.format(Date(this))
}

fun LocalDate.toStringByFormat(dateFormat: String, locale: Locale = Locale.getDefault()): String {
    val formatter = DateTimeFormatter.ofPattern(dateFormat, locale)
    return this.format(formatter)
}

//@RequiresApi(Build.VERSION_CODES.O)
//fun LocalDate.toGetNowMilis(): Long {
//    return LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
//}

fun LocalDate.toStringByFormat(dateFormat: String): String =
    this.format(DateTimeFormatter.ofPattern(dateFormat))

fun LocalDate.toRuStringByFormat(dateFormat: String): String {
    val formatter = DateTimeFormatter.ofPattern(dateFormat, Locale("ru", "RU"))
    return this.format(formatter)
}

fun LocalDate.toWeekDay(): String = this.toStringByFormat(DATE_PATTERN_WEEK_DAY)

fun LocalDate.toRuWeekDay(): String = this.toRuStringByFormat(DATE_PATTERN_WEEK_DAY)

fun LocalDate.toDayOnly(): String = this.toStringByFormat(DATE_PATTERN_DAY_ONLY)

fun LocalDate.toGetFirstDayOfWeek(): LocalDate {
    /*** dayOfWeek: понедельник - 1 ... воскресенье -7*/
    return this.minusDays(this.dayOfWeek.value.toLong() - 1)
}

fun LocalDate.toGetAllDayOfMonth(): List<LocalDate> {
    val firstOfMonth = LocalDate.of(this.year, this.month, 1)
    val lastOfMonth = firstOfMonth.withDayOfMonth(firstOfMonth.lengthOfMonth())

    val daysInMonth = mutableListOf<LocalDate>()
    var current = firstOfMonth

    while (current <= lastOfMonth) {
        daysInMonth.add(current)
        current = current.plusDays(1)
    }
    return daysInMonth
}

fun LocalDate.toGetDaysOfWeekInMonth(ruShortDayOfWeek: String): List<LocalDate> {
    val daysOfWeekInRussian =
        DayOfWeek.entries.map { it.getDisplayName(TextStyle.SHORT, Locale("ru", "RU")) }
    val pos = daysOfWeekInRussian.indexOf(ruShortDayOfWeek)
    if (pos >= 0) {
        /*        val daysInMonth = mutableListOf<LocalDate>()
                val firstOfMonth = LocalDate.of(this.year, this.month, 1)
                val lastOfMonth = firstOfMonth.withDayOfMonth(firstOfMonth.lengthOfMonth())
                val dayOfWeekfirstOfMonth = firstOfMonth.dayOfWeek.value
                if (pos > dayOfWeekfirstOfMonth) {

                }*/
        val allDayOfMonth = this.toGetAllDayOfMonth()
        return allDayOfMonth.filter { it.toRuWeekDay() == ruShortDayOfWeek }
    } else {
        return emptyList<LocalDate>()
    }
}

//val currentDate = LocalDate.now()
//println(currentDate.dayOfWeek)
//println(currentDate.dayOfWeek.value)
//println(currentDate.toWeekDay())
//println(currentDate.toRuWeekDay())
//println(currentDate.dayOfMonth)
//println(currentDate.toDayOnly())
//println(currentDate.toStringByFormat(DATE_PATTERN_DEFAULT,Locale("ru", "RU")))
//println(currentDate.toRuStringByFormat(DATE_PATTERN_FULL_DATE_AND_TEXT_MONTH))
//println(currentDate.toStringByFormat(DATE_PATTERN_FULL_DATE_AND_TEXT_MONTH,Locale("ru", "RU")))
