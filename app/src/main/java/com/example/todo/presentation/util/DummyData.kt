package com.example.todo.presentation.util

import com.example.todo.data.wrapper.DateTimeProviderImpl
import com.example.todo.domian.model.*
import java.time.LocalDateTime


fun dummyList() = buildList(
    muchList = 5,
    muchTask = 10,
)

fun dummyCategory() = listOf(
    Category.Today(emptyList()),
    Category.Scheduled(emptyList()),
    Category.All(emptyList()),
)

private fun buildList(
    muchList: Int,
    muchTask: Int,
): List<ToDoList> {
    val now = DateTimeProviderImpl().now()
    return build(muchList) {
        ToDoList(
            id = "List$it",
            name = "List$it",
            color = ToDoColor.values().random(),
            tasks = buildTask(muchTask, now),
            createdAt = now,
            updatedAt = now
        )
    }
}

private fun buildTask(
    muchTask: Int,
    now: LocalDateTime,
): List<ToDoTask> {
    return build(muchTask) {
        ToDoTask(
            id = "$it",
            name = "Task$it",
            status = ToDoStatus.values().random(),
            createdAt = now,
            updatedAt = now
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
