package com.example.todo.data.repository

import com.example.todo.data.datasource.local.LocalManager
import com.example.todo.data.datasource.local.extension.toToDoListDb
import com.example.todo.data.datasource.local.extension.toToDoTask
import com.example.todo.data.datasource.local.model.ToDoListDb
import com.example.todo.data.datasource.local.model.ToDoTaskDb
import com.example.todo.domian.model.*
import com.example.todo.domian.repository.ToDoRepo
import com.example.todo.presentation.util.getCurrentDate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject

class ToDoRepoImpl @Inject constructor(
    private val localManager: LocalManager
) : ToDoRepo {

    override suspend fun createToDoList(title: String, color: ToDoColor) {
        localManager.insertList(
            ToDoListDb(
                id = UUID.randomUUID().toString(),
                name = title,
                color = color,
                createdAt = getCurrentDate(),
                updatedAt = getCurrentDate()
            )
        )
    }

    override suspend fun updateToDoList(toDoList: ToDoList) {
        localManager.updateList(toDoList.toToDoListDb())
    }

    override suspend fun deleteToDoList(id: String) {
        localManager.deleteList(id)
    }

    override fun getToDoList(id: String): Flow<ToDoList> {
        return localManager.getListById(id)
    }

    override fun getAllToDoLists(): Flow<List<ToDoList>> {
        return localManager.getLists()
    }

    override fun getAllToDoCategory(): Flow<List<Category>> {
        return flow {
            emit(
                listOf(
                    Category.Today(
                        localManager.getToDayTasks().first().map { it.toToDoTask() }
                    ),
                    Category.All(
                        localManager.getAllTasks().first().map { it.toToDoTask() }
                    ),
                    Category.Scheduled(
                        localManager.getScheduledTasks().first().map { it.toToDoTask() }
                    )
                )
            )
        }
    }

    override suspend fun createToDoTask(
        name: String,
        note: String,
        duDate: Long?,
        listId: String
    ) {
        localManager.insertTask(
            ToDoTaskDb(
                id = UUID.randomUUID().toString(),
                listId = listId,
                name = name,
                note = note,
                status = ToDoStatus.IN_PROGRESS,
                dueDate = duDate,
                createdAt = getCurrentDate(),
                updatedAt = getCurrentDate(),
                isDueDateTimeSet = duDate != null,
                completedAt = null
            )
        )
    }

    override suspend fun updateToDoTask(toDoTask: ToDoTask) {
        localManager.updateTask(
            id = toDoTask.id,
            name = toDoTask.name,
            note = toDoTask.note,
            status = toDoTask.status,
            dueDate = toDoTask.dueDate,
            createdAt = toDoTask.createdAt,
            updatedAt = getCurrentDate(),
            isDueDateTimeSet = toDoTask.isDueDateTimeSet,
            completedAt = toDoTask.completedAt
        )
    }

    override suspend fun deleteToDoTask(id: String) {
        localManager.deleteTask(id)
    }

}