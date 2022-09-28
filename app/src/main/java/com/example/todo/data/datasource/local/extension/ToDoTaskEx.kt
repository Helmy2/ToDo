package com.example.todo.data.datasource.local.extension

import com.example.todo.data.datasource.local.model.ToDoTaskDb
import com.example.todo.domian.model.ToDoTask

fun ToDoTaskDb.toToDoTask() =
    ToDoTask(
        id,
        name,
        status,
        completedAt,
        dueDate,
        note,
        createdAt,
        updatedAt
    )


fun ToDoTask.toToDoTaskDb(listId: String) =
    ToDoTaskDb(
        id,
        name,
        listId = listId,
        status,
        dueDate,
        completedAt,
        note,
        createdAt,
        updatedAt
    )