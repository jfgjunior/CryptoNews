package com.jfapps.cryptonews.extensions

import java.text.SimpleDateFormat
import java.util.*

val Calendar.today: String
    get() = passedDays(0)

fun Calendar.passedDays(days: Int): String {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR, -days)
    return dateToISO8601(calendar.time)
}

fun dateToISO8601(date: Date): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.US)
    dateFormat.timeZone = TimeZone.getDefault()
    return dateFormat.format(date)
}

fun Calendar.toWritten(dateISO: String): String {
    var dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    val date = dateFormat.parse(dateISO)
    dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.US)
    return dateFormat.format(date)
}