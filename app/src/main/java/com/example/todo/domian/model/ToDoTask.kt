package com.example.todo.domian.model

import java.time.LocalDateTime

data class ToDoTask(
    val id: String = "",
    val name: String = "",
    val status: ToDoStatus = ToDoStatus.IN_PROGRESS,
    val completedAt: LocalDateTime? = null,
    val dueDate: LocalDateTime? = null,
    val note: String = "",
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null,
) {
    fun isComplete() = status == ToDoStatus.COMPLETE
    fun isDueDateTimeSet(): Boolean = dueDate != null
}
