package com.example.todo.data.repository

import com.example.todo.data.datasource.local.LocalManager
import com.example.todo.data.datasource.local.extension.toToDoListDb
import com.example.todo.data.datasource.local.extension.toToDoTask
import com.example.todo.data.datasource.local.extension.toToDoTaskDb
import com.example.todo.data.datasource.local.model.ToDoListDb
import com.example.todo.data.wrapper.DateTimeProvider
import com.example.todo.data.wrapper.IdProvider
import com.example.todo.domian.model.*
import com.example.todo.domian.repository.ToDoRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject

class ToDoRepoImpl @Inject constructor(
    private val localManager: LocalManager,
    private val dateTimeProvider: DateTimeProvider,
    private val idProvider: IdProvider
) : ToDoRepo {

    override suspend fun createToDoList(toDoList: ToDoList) {
        localManager.insertList(
            toDoList.copy(
                id = idProvider.generate(),
                createdAt = dateTimeProvider.now(),
                updatedAt = dateTimeProvider.now()
            ).toToDoListDb()
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
        task: ToDoTask,
        listId: String
    ) {
        localManager.insertTask(
            task.copy(
                id = idProvider.generate(),
                createdAt = dateTimeProvider.now(),
                updatedAt = dateTimeProvider.now(),
            ).toToDoTaskDb(listId)
        )
    }

    override suspend fun updateToDoTask(toDoTask: ToDoTask, listId: String) {
        localManager.updateTask(
            toDoTask.copy(updatedAt = dateTimeProvider.now()).toToDoTaskDb(listId)
        )
    }

    override suspend fun deleteToDoTask(id: String) {
        localManager.deleteTask(id)
    }

}