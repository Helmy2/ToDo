package com.example.todo.presentation.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.domian.model.ToDoList
import com.example.todo.domian.model.ToDoTask
import com.example.todo.domian.repository.ToDoRepo
import com.example.todo.presentation.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repo: ToDoRepo,
) : ViewModel() {

    private val listId: String = checkNotNull(savedStateHandle[Screens.ListScreen.argument0])

    val toDoListFlow: Flow<ToDoList> = repo.getToDoList(listId)

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

    fun updateToDoList(toDoList: ToDoList) {
        viewModelScope.launch {
            repo.updateToDoList(toDoList)
        }
    }
}