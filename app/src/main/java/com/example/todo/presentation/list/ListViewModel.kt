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

    fun addToDoTask(task: ToDoTask, listId: String) =
        viewModelScope.launch {
            repo.createToDoTask(task, listId)
        }

    fun updateToDoTask(task: ToDoTask, listId: String) {
        viewModelScope.launch {
            repo.updateToDoTask(task, listId)
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