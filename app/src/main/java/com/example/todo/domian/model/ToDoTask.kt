package com.example.todo.domian.model

data class ToDoTask(
    val id: String = "",
    val listId: String = "",
    val name: String = "",
    val status: ToDoStatus = ToDoStatus.IN_PROGRESS,
    val completedAt: Long? = null,
    val dueDate: Long? = null,
    val isDueDateTimeSet: Boolean = false,
    val repeat: ToDoRepeat = ToDoRepeat.NEVER,
    val note: String = "",
    val noteUpdatedAt: Long? = null,
    val createdAt: Long,
    val updatedAt: Long,
)
