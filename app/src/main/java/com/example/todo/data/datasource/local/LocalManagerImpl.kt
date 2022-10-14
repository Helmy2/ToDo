package com.example.todo.data.datasource.local

import com.example.todo.data.datasource.local.extension.toToDoList
import com.example.todo.data.datasource.local.extension.toToDoTask
import com.example.todo.data.datasource.local.model.ToDoListDb
import com.example.todo.data.datasource.local.model.ToDoTaskDb
import com.example.todo.domian.model.ToDoList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val TAG = "LocalManagerImpl"

class LocalManagerImpl @Inject constructor(
    private val toDoDao: ToDoDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : LocalManager {
    override suspend fun insertList(data: ToDoListDb) {
        withContext(dispatcher) {
            toDoDao.insertList(data)
        }
    }

    override fun getLists(): Flow<List<ToDoList>> {
        return toDoDao.getListWithTask().filterNotNull().map { it.map { it.toToDoList() } }
    }

    override fun getListById(id: String): Flow<ToDoList> {
        return toDoDao.getListWithTaskById(id).filterNotNull().map { it.toToDoList() }
    }

    override suspend fun deleteList(listId: String) {
        withContext(dispatcher) {
            toDoDao.deleteList(listId)
        }
    }

    override suspend fun updateList(data: ToDoListDb) {
        withContext(dispatcher) {
            toDoDao.updateList(data)
        }
    }

    override suspend fun insertTask(toDoTaskDb: ToDoTaskDb) {
        withContext(dispatcher) {
            toDoDao.insertTask(toDoTaskDb)
        }
    }

    override suspend fun deleteTask(id: String) {
        withContext(dispatcher) {
            toDoDao.deleteTask(id)
        }
    }

    override suspend fun updateTask(task: ToDoTaskDb) {
        withContext(dispatcher) {
            toDoDao.updateTask(task)
        }
    }

    override fun getAllTasks(): Flow<List<ToDoList>> {
        return toDoDao.getListWithTask().filterNotNull().map { it.map { it.toToDoList() } }
    }

    override fun search(value: String): Flow<List<ToDoList>> {
        return toDoDao.search("%$value%").map { toListDb(it) }
    }

    private suspend fun toListDb(tasks: List<ToDoTaskDb>): List<ToDoList> {
        return withContext(Dispatchers.IO) {
            val list = mutableListOf<ToDoList>()
            tasks.forEach { task ->
                val currentList = toDoDao.getListById(task.listId).toToDoList()
                var item = list.find { it.id == currentList.id }
                item = if (item == null) {
                    currentList.copy(tasks = listOf(task.toToDoTask()))
                } else {
                    list.remove(item)
                    item.copy(tasks = item.tasks + task.toToDoTask())
                }
                list.add(item)
            }
            return@withContext list
        }
    }
}