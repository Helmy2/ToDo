package com.example.todo.domian.repository

import com.example.todo.domian.model.ToDoList
import com.example.todo.domian.model.ToDoTask

interface ToDoRepo {
    suspend fun createToDoList(toDoList: ToDoList)
    suspend fun updateToDoList(toDoList: ToDoList)
    suspend fun deleteToDoList(id: String)
    suspend fun getToDoList(id: String): Result<ToDoList>

    suspend fun getAllToDoLists(): Result<List<ToDoList>>

    suspend fun createToDoTask(toDoTask: ToDoTask)
    suspend fun updateToDoTask(toDoTask: ToDoTask)
    suspend fun deleteToDoTask(toDoTask: ToDoTask)
    suspend fun getToDoTask(id: String): Result<ToDoTask>
}