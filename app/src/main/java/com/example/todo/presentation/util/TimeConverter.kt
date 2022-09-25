package com.example.todo.presentation.util

import java.text.SimpleDateFormat
import java.util.*


fun Long.convertLongToTime(): String {
    val date = Date(this)
    val locale = Locale.getDefault()
    val formatter = SimpleDateFormat("E, d MMM y | h:mm a", locale)
    return formatter.format(date)
}

fun String.convertDateToLong(): Long {
    val locale = Locale.getDefault()
    val formatter = SimpleDateFormat("E, d MMM y | h:mm a", locale)
    return formatter.parse(this)!!.time
}


fun getCurrentDate(): Long = Calendar.getInstance().timeInMillis

