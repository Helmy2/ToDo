package com.example.todo.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.domian.model.ToDoList
import com.example.todo.domian.model.ToDoTask
import com.example.todo.domian.repository.ToDoRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repo: ToDoRepo,
) : ViewModel() {
    fun getToDoList(id: String): Flow<ToDoList> {
        return repo.getToDoList(id)
    }

    fun addToDoTask(title: String, note: String, duDate: Long?, listId: String) =
        viewModelScope.launch {
            repo.createToDoTask(title, note, duDate, listId)
        }

    fun updateToDoTask(task: ToDoTask) {
        viewModelScope.launch {
            repo.updateToDoTask(task)
        }
    }

    fun deleteToDoTask(id: String) {
        viewModelScope.launch {
            repo.deleteToDoTask(id)
        }
    }
}