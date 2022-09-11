package com.example.todo.data.datasource.local.extension

import com.example.todo.data.datasource.local.model.ToDoListDbWithToDoTaskDb
import com.example.todo.domian.model.ToDoList

fun ToDoListDbWithToDoTaskDb.toToDoList(): ToDoList {
    return ToDoList(
        id = toDoListDb.id,
        name = toDoListDb.name,
        color = toDoListDb.color,
        tasks = toDoTaskDbList.map { it.toToDoTask() },
        createdAt = toDoListDb.createdAt,
        updatedAt = toDoListDb.updatedAt
    )
}
