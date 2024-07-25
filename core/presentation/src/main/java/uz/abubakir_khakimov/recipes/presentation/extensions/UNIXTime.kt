package uz.abubakir_khakimov.recipes.presentation.extensions

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

const val DEFAULT_DATE_PATTERN = "dd.MM.yyyy" //00.00.0000
const val INFORMATIVE_DATE_PATTERN = "dd MMM yyyy" //00 oct. 0000
const val INFORMATIVE_SHORT_DATE_PATTERN = "dd MMM" //00 oct.
const val INFORMATIVE_LONG_DATE_PATTERN = "dd MMMM" //00 october.
const val INFORMATIVE_LARGE_LONG_DATE_PATTERN = "EEEE, dd MMMM, yyyy" //Monday, 00 october, 0000
const val DEFAULT_TIME_PATTERN = "HH:mm" //00:00
const val DEFAULT_DATE_AND_TIME_PATTERN = "dd.MM.yyyy HH:mm" //00.00.0000 00:00
const val INFORMATIVE_SHORT_DATE_AND_TIME_PATTERN = "dd MMM HH:mm" //00 oct. 00:00
const val INFORMATIVE_LONG_DATE_AND_TIME_PATTERN = "dd MMMM HH:mm" //00 october. 00:00
const val WEEK_DAY_PATTERN = "EEEE" //Monday
const val INFORMATIVE_SHORT_DATE_WITH_WEEK_DAY_PATTERN = "EEE, dd MMM" //Mon, 00 oct.

const val ONE_DAY_IN_SECONDS = 86400L
const val ONE_DAY_IN_MILLIS = 86400000L
const val ONE_WEEK_IN_SECONDS = 604800L
const val ONE_WEEK_IN_MILLIS = 604800000L

/**
 *This is function convert from (UNIX) UTC timestamp format to simple mobile readable date format.
 *You can choose one of the above patterns as an argument.
 *Or you can enter the format pattern you need as a string.
 */
fun Long.convertToPattern(pattern: String, locale: Locale = Locale.getDefault()): String =
    SimpleDateFormat(pattern, locale).let {
        it.timeZone = TimeZone.getDefault()
        it.format(
            Date(convertToMillis())
        )
    }

/**
 *This is function convert from UNIX seconds to UNIX milliseconds.
 */
fun Long.convertToMillis(): Long = (this * 1000)

/**
 *This is function convert from UNIX milliseconds to UNIX seconds.
 */
fun Long.convertToSec(): Long = (this / 1000)

fun getCurrentDayStartSec(needToCorrectTime: Long? = null): Long =
    getCurrentDayStartMillis(needToCorrectTime = needToCorrectTime?.convertToMillis())
        .convertToSec()

fun getCurrentDayStartMillis(needToCorrectTime: Long? = null): Long = Calendar.getInstance()
    .let { calendar ->
        if (needToCorrectTime != null) calendar.timeInMillis = needToCorrectTime
        calendar.set(Calendar.MILLISECOND, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.timeInMillis
    }

fun getCurrentDayEndSec(needToCorrectTime: Long? = null): Long =
    getCurrentDayEndMillis(needToCorrectTime = needToCorrectTime?.convertToMillis()).convertToSec()

fun getCurrentDayEndMillis(needToCorrectTime: Long? = null): Long = Calendar.getInstance()
    .let { calendar ->
        if (needToCorrectTime != null) calendar.timeInMillis = needToCorrectTime
        calendar.set(Calendar.MILLISECOND, 999)
        calendar.set(Calendar.SECOND, 59)
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.timeInMillis
    }

fun Long.nextDay(): Long = plus(other = ONE_DAY_IN_SECONDS)

fun Long.previousDay(): Long = minus(other = ONE_DAY_IN_SECONDS)

fun getCurrentWeekStartSec(needToCorrectTime: Long? = null): Long =
    getCurrentWeekStartMillis(
        needToCorrectTime = needToCorrectTime?.convertToMillis()
    ).convertToSec()

fun getCurrentWeekStartMillis(needToCorrectTime: Long? = null): Long =
    (needToCorrectTime ?: System.currentTimeMillis())
        .getWeekMillisByDayPosition(dayPosition = Calendar.MONDAY)

fun getCurrentWeekEndSec(needToCorrectTime: Long? = null): Long =
    getCurrentWeekEndMillis(
        needToCorrectTime = needToCorrectTime?.convertToMillis()
    ).convertToSec()

fun getCurrentWeekEndMillis(needToCorrectTime: Long? = null): Long =
    (needToCorrectTime ?: System.currentTimeMillis())
        .getWeekMillisByDayPosition(dayPosition = Calendar.SUNDAY).let {
            getCurrentDayEndMillis(needToCorrectTime = it)
        }

fun Long.nextWeek(): Long = plus(other = ONE_WEEK_IN_SECONDS)

fun Long.previousWeek(): Long = minus(other = ONE_WEEK_IN_SECONDS)

fun Long.getWeekDaysSec(): List<Long> = convertToMillis().getWeekDaysMillis()
    .map { it.convertToSec() }

fun Long.getWeekDaysMillis(): List<Long> {
    val days = ArrayList<Long>()

    for (dayPosition in 2..8){
        getWeekMillisByDayPosition(
            dayPosition = if (dayPosition == 8) 1 else dayPosition
        ).also {
            days.add(it)
        }
    }

    return days
}

fun Long.setTimeSec(hour: Int, min: Int): Long = convertToMillis()
    .setTimeMillis(hour = hour, min = min).convertToSec()

fun Long.setTimeMillis(hour: Int, min: Int): Long = Calendar.getInstance()
    .let { calendar ->
        calendar.timeInMillis = this
        calendar.set(/* field = */ Calendar.MILLISECOND, /* value = */ 0)
        calendar.set(/* field = */ Calendar.SECOND, /* value = */ 0)
        calendar.set(/* field = */ Calendar.MINUTE, /* value = */ min)
        calendar.set(/* field = */ Calendar.HOUR_OF_DAY, /* value = */ hour)
        calendar.timeInMillis
    }

fun Long.getWeekMillisByDayPosition(dayPosition: Int): Long = Calendar.getInstance()
    .let { calendar ->
        calendar.timeInMillis = this
        calendar.firstDayOfWeek = Calendar.MONDAY
        calendar.set(/* field = */ Calendar.MILLISECOND, /* value = */ 0)
        calendar.set(/* field = */ Calendar.SECOND, /* value = */ 0)
        calendar.set(/* field = */ Calendar.MINUTE, /* value = */ 0)
        calendar.set(/* field = */ Calendar.HOUR_OF_DAY, /* value = */ 0)
        calendar.set(/* field = */ Calendar.DAY_OF_WEEK, /* value = */ dayPosition)
        calendar.timeInMillis
    }