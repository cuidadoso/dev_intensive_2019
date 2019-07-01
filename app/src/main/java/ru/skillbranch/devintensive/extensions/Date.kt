package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*


const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR
const val MONTH = 30 * DAY
const val YEAR = DAY * 365

fun Date.format(pattern:String="HH:mm:ss dd.MM.yy"):String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value:Int, units:TimeUnit = TimeUnit.SECOND) : Date {
    var time = this.time

    time += when (units) {
        TimeUnit.SECOND -> value * SECOND
        TimeUnit.MINUTE -> value * MINUTE
        TimeUnit.HOUR -> value * HOUR
        TimeUnit.DAY -> value * DAY
        TimeUnit.MONTH -> value * MONTH
        TimeUnit.YEAR -> value * YEAR
    }
    this.time = time
    return this
}

enum class TimeUnit {
    SECOND,
    MINUTE,
    HOUR,
    DAY,
    MONTH,
    YEAR
}

fun Date.humanizedDiff(date: Date = Date()): String {
    val elapsed = date.time - this.time
    return when {
        elapsed < 10 * SECOND -> "несколько секунд назад"
        elapsed < MINUTE -> "меньше чем минуту назад"
        elapsed < HOUR -> "${when(elapsed / MINUTE){
            1L -> "${elapsed / MINUTE} минуту"
            2L, 3L, 4L -> "${elapsed / MINUTE} минуты"
            else -> "${elapsed / MINUTE} минут"
        }} назад"
        elapsed < DAY -> "${when(elapsed / HOUR){
            1L -> "${elapsed / HOUR} час"
            2L, 3L, 4L -> "${elapsed / HOUR} часа"
            else -> "${elapsed / HOUR} часов"
        }} назад"
        elapsed < MONTH -> "${when(elapsed / DAY){
            1L -> "${elapsed / DAY} день"
            2L, 3L, 4L -> "${elapsed / DAY} дня"
            else -> "${elapsed / DAY} дней"
        }} назад"
        elapsed < YEAR -> "${when(elapsed / MONTH){
            1L -> "${elapsed / MONTH} месяц"
            2L, 3L, 4L -> "${elapsed / MONTH} месяца"
            else -> "${elapsed / MONTH} месяцев"
        }} назад"
        else -> " больше года назад"
    }
}
