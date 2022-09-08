package com.example.todo.data.datasource.local

import com.example.todo.data.datasource.local.extension.toToDoList
import com.example.todo.data.datasource.local.extension.toToDoListDb
import com.example.todo.data.datasource.local.model.ToDoListDb
import com.example.todo.domian.model.ToDoList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalManagerImpl @Inject constructor(
    private val toDoDao: ToDoDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : LocalManager {
    override suspend fun insertList(data: ToDoList) {
        withContext(dispatcher) {
            toDoDao.insertList(data.toToDoListDb())
        }
    }

    override fun getLists(): Flow<List<ToDoList>> {
        return toDoDao.getLists().filterNotNull().map { it.map { it.toToDoList() } }
    }

    override fun getListById(id: String): Flow<ToDoList> {
        return toDoDao.getListById(id).filterNotNull().map { it.toToDoList() }
    }

    override suspend fun deleteList(listId: String) {
        withContext(dispatcher) {
            toDoDao.deleteList(listId)
        }
    }

    override suspend fun updateList(data: ToDoList) {
        withContext(dispatcher) {
            toDoDao.updateList(data.toToDoListDb())
        }
    }
}