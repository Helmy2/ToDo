package com.example.todo.data.datasource.local


import com.example.todo.data.datasource.local.model.ToDoListDb
import com.example.todo.data.datasource.local.model.ToDoTaskDb
import com.example.todo.domian.model.ToDoList
import com.example.todo.domian.model.ToDoStatus
import com.example.todo.domian.model.ToDoTask
import kotlinx.coroutines.flow.Flow

interface LocalManager {
    suspend fun insertList(data: ToDoListDb)
    fun getLists(): Flow<List<ToDoList>>
    fun getListById(id: String): Flow<ToDoList>
    suspend fun deleteList(listId: String)
    suspend fun updateList(data: ToDoListDb)
    suspend fun insertTask(toDoTaskDb: ToDoTaskDb)
    suspend fun deleteTask(id: String)
    suspend fun updateTask(
        id: String,
        name: String,
        status: ToDoStatus,
        completedAt: Long?,
        dueDate: Long?,
        isDueDateTimeSet: Boolean,
        note: String,
        createdAt: Long,
        updatedAt: Long,
    )

    fun getAllTasks(): Flow<List<ToDoTaskDb>>
    fun getToDayTasks(): Flow<List<ToDoTaskDb>>
    fun getScheduledTasks(): Flow<List<ToDoTaskDb>>
}

