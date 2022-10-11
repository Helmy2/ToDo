package com.example.todo.util

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun LocalDateTime.formatDateTime(): String {
    return this.format(DateTimeFormatter.ofPattern("E, d MMM y | h:mm a"))
}

fun LocalDateTime.toMillis(): Long {
    val zoneId = ZoneId.systemDefault()
    return atZone(zoneId).toInstant().toEpochMilli()
}

fun Long.toLocalDateTime(): LocalDateTime {
    val zoneId = ZoneId.systemDefault()
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(this), zoneId)
}