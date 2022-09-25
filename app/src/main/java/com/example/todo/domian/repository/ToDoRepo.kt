package com.example.todo.domian.repository

import com.example.todo.domian.model.Category
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
    fun getAllToDoCategory(): Flow<List<Category>>

    suspend fun createToDoTask(name: String, note: String, duDate: Long?, listId: String)
    suspend fun updateToDoTask(toDoTask: ToDoTask)
    suspend fun deleteToDoTask(id: String)
}