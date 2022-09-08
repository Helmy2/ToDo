package com.example.todo.domian.repository

import com.example.todo.domian.model.ToDoColor
import com.example.todo.domian.model.ToDoList
import com.example.todo.domian.model.ToDoTask
import kotlinx.coroutines.flow.Flow

interface ToDoRepo {
    suspend fun createToDoList(title: String, color: ToDoColor)
    suspend fun updateToDoList(toDoList: ToDoList)
    suspend fun deleteToDoList(id: String)
    fun getToDoList(id: String): Flow<ToDoList>

    fun getAllToDoLists(): Flow<List<ToDoList>>

    suspend fun createToDoTask(toDoTask: ToDoTask)
    suspend fun updateToDoTask(toDoTask: ToDoTask)
    suspend fun deleteToDoTask(toDoTask: ToDoTask)
    suspend fun getToDoTask(id: String): Result<ToDoTask>
}