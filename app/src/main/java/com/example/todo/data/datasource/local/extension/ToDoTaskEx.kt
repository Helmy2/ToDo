package com.example.todo.data.datasource.local.extension

import com.example.todo.data.datasource.local.model.ToDoTaskDb
import com.example.todo.domian.model.ToDoTask

fun ToDoTaskDb.toToDoTask(): ToDoTask {
    return ToDoTask(
        id,  name, status, completedAt, dueDate, isDueDateTimeSet, note, createdAt, updatedAt
    )
}