package com.example.todo.presentation.util

import com.example.todo.domian.model.ToDoColor
import com.example.todo.domian.model.ToDoList
import com.example.todo.domian.model.ToDoStatus
import com.example.todo.domian.model.ToDoTask


fun dummyRandomList() = buildList(
    muchList = 10,
    muchTask = 10,
)

fun dummyRandomCategory() = buildList(
    muchList = 3,
    muchTask = 10,
)

private fun buildList(
    muchList: Int,
    muchTask: Int,
): List<ToDoList> {
    return build(muchList) {
        ToDoList(
            id = "list$it",
            name = "List$it",
            color = ToDoColor.values().random(),
            tasks = buildTask(muchTask),
            createdAt = getCurrentDate(),
            updatedAt = getCurrentDate()
        )
    }
}

private fun buildTask(
    muchTask: Int,
): List<ToDoTask> {
    return build(muchTask) {
        ToDoTask(
            id = "task$it",
            name = "Task$it",
            status = ToDoStatus.values().random(),
            createdAt = getCurrentDate(),
            updatedAt = getCurrentDate()
        )
    }
}

private fun <T> build(much: Int, obj: (Int) -> T): List<T> {
    val list = mutableListOf<T>()
    for (i in 0 until much) {
        list.add(obj(i))
    }
    return list
}
