package com.example.todo.data.wrapper

import java.time.LocalDateTime
import java.util.*

interface DateTimeProvider {
    fun now(): LocalDateTime
}

class DateTimeProviderImpl : DateTimeProvider {
    override fun now(): LocalDateTime {
        return LocalDateTime.now()
    }
}
