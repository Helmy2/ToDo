package com.example.todo.data.datasource.local

import android.util.Log
import com.example.todo.data.datasource.local.extension.toToDoList
import com.example.todo.data.datasource.local.model.ToDoListDb
import com.example.todo.data.datasource.local.model.ToDoTaskDb
import com.example.todo.domian.model.ToDoList
import com.example.todo.domian.model.ToDoStatus
import com.example.todo.presentation.util.convertLongToTime
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.util.*
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
        return toDoDao.getListById(id).filterNotNull().map { it.toToDoList() }
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

    override suspend fun updateTask(
        id: String,
        name: String,
        status: ToDoStatus,
        completedAt: Long?,
        dueDate: Long?,
        isDueDateTimeSet: Boolean,
        note: String,
        createdAt: Long,
        updatedAt: Long,
    ) {
        withContext(dispatcher) {
            toDoDao.updateTask(
                id,
                name,
                status,
                completedAt,
                dueDate,
                isDueDateTimeSet,
                note,
                createdAt,
                updatedAt
            )
        }
    }

    override fun getAllTasks(): Flow<List<ToDoTaskDb>> {
        return toDoDao.getAllTasks()
    }

    override fun getToDayTasks(): Flow<List<ToDoTaskDb>> {
        return toDoDao.getTasksFromTo(startOfDay(),endOfDay())
    }

    override fun getScheduledTasks(): Flow<List<ToDoTaskDb>> {
        return toDoDao.getTasksFrom(startOfDay())
    }

    private fun startOfDay(): Long {
        val cal = Calendar.getInstance()
        cal[Calendar.HOUR_OF_DAY] = 0
        cal[Calendar.MINUTE] = 0
        cal[Calendar.SECOND] = 0
        cal[Calendar.MILLISECOND] = 0
        return cal.timeInMillis
    }

    private fun endOfDay(): Long {
        val cal = Calendar.getInstance()
        cal[Calendar.HOUR_OF_DAY] = 24
        cal[Calendar.MINUTE] = 0
        cal[Calendar.SECOND] = 0
        cal[Calendar.MILLISECOND] = 0
        return cal.timeInMillis
    }
}