package com.example.todo.data.datasource.local


import com.example.todo.data.datasource.local.model.ToDoListDb
import com.example.todo.domian.model.ToDoList
import kotlinx.coroutines.flow.Flow

interface LocalManager {
    suspend fun insertList(data: ToDoList)
    fun getLists(): Flow<List<ToDoList>>
    fun getListById(id: String): Flow<ToDoList>
    suspend fun deleteList(listId: String)
    suspend fun updateList(data: ToDoList)
}

