package com.example.todo.presentation.util

import com.example.todo.domian.model.*
import java.time.LocalDateTime


fun dummyRandomList() = buildList(
    muchList = 10,
    muchTask = 10,
    muchStep = 5
)

fun dummyRandomCategory() = buildList(
    muchList = 3,
    muchTask = 10,
    muchStep = 5
)

private fun buildList(
    muchList: Int,
    muchTask: Int,
    muchStep: Int
): List<ToDoList> {
    return build(muchList) {
        ToDoList(
            id = "list$it",
            name = "List$it",
            color = ToDoColor.values().random(),
            tasks = buildTask(muchTask, muchStep),
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
    }
}

private fun buildTask(
    muchTask: Int,
    muchStep: Int
): List<ToDoTask> {
    return build(muchTask) {
        ToDoTask(
            id = "task$it",
            name = "Task$it",
            status = ToDoStatus.values().random(),
            steps = buildStep(muchStep),
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
    }
}

private fun buildStep(muchStep: Int): List<ToDoStep> {
    return build(muchStep) {
        ToDoStep(
            id = "step$it",
            name = "Step$it",
            status = ToDoStatus.IN_PROGRESS,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
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
