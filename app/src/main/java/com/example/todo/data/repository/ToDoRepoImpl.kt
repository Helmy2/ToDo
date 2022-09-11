package com.example.todo.data.repository

import com.example.todo.data.datasource.local.LocalManager
import com.example.todo.data.datasource.local.model.ToDoListDb
import com.example.todo.data.datasource.local.model.ToDoTaskDb
import com.example.todo.domian.model.ToDoColor
import com.example.todo.domian.model.ToDoList
import com.example.todo.domian.model.ToDoStatus
import com.example.todo.domian.model.ToDoTask
import com.example.todo.domian.repository.ToDoRepo
import com.example.todo.presentation.util.dummyRandomCategory
import com.example.todo.presentation.util.getCurrentDate
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

class ToDoRepoImpl @Inject constructor(
    private val localManager: LocalManager
) : ToDoRepo {
    val list = mutableListOf<ToDoList>()

    init {
        list.addAll(dummyRandomCategory())
    }

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
        localManager.updateList(toDoList)
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
    }

    override suspend fun deleteToDoTask(toDoTask: ToDoTask) {
    }

    override suspend fun getToDoTask(id: String): Result<ToDoTask> {
        return Result.success(list.first().tasks.first())
    }

}