package com.example.todo.data.datasource.local.model

import androidx.room.Embedded
import androidx.room.Relation

data class ToDoListDbWithToDoTaskDb(
    @Embedded val toDoListDb: ToDoListDb,
    @Relation(
        parentColumn = "list_id",
        entityColumn = "task_list_id"
    )
    val toDoTaskDbList: List<ToDoTaskDb>
)