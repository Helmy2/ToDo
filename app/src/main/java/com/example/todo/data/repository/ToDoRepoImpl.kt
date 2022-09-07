package com.example.todo.data.repository

import com.example.todo.domian.model.ToDoList
import com.example.todo.domian.model.ToDoTask
import com.example.todo.domian.repository.ToDoRepo
import com.example.todo.presentation.util.dummyRandomCategory
import com.example.todo.presentation.util.dummyRandomList
import okhttp3.internal.toImmutableList

class ToDoRepoImpl : ToDoRepo {
    val list = mutableListOf<ToDoList>()

    init {
        list.addAll(dummyRandomCategory())
    }

    override suspend fun createToDoList(toDoList: ToDoList) {
        list.add(toDoList)
    }

    override suspend fun updateToDoList(toDoList: ToDoList) {
        list.removeIf { it.id == toDoList.id }
        list.add(toDoList)
    }

    override suspend fun deleteToDoList(id: String) {
        list.removeIf { it.id == id }
    }

    override suspend fun getToDoList(id: String): Result<ToDoList> {
        return try {
            Result.success(list.find { it.id == id }!!)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getAllToDoLists(): Result<List<ToDoList>> {
        return Result.success(list.toImmutableList())
    }

    override suspend fun createToDoTask(toDoTask: ToDoTask) {
    }

    override suspend fun updateToDoTask(toDoTask: ToDoTask) {
    }

    override suspend fun deleteToDoTask(toDoTask: ToDoTask) {
    }

    override suspend fun getToDoTask(id: String): Result<ToDoTask> {
        return Result.success(list.first().tasks.first())
    }

}