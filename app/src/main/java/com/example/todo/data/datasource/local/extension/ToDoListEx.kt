package com.example.todo.data.datasource.local.extension

import com.example.todo.data.datasource.local.model.ToDoListDb
import com.example.todo.domian.model.ToDoList

fun ToDoListDb.toToDoList(): ToDoList {
    return ToDoList(
        id = id,
        name = name,
        color = color,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun ToDoList.toToDoListDb(): ToDoListDb {
    return ToDoListDb(
        id = id,
        name = name,
        color = color,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}