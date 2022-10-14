package com.example.todo.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.domian.model.ToDoList
import com.example.todo.domian.model.ToDoTask
import com.example.todo.domian.repository.ToDoRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "HomeViewModel"

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: ToDoRepo,
) : ViewModel() {
    val toDoListFlow = repo.getAllToDoLists()
    private val _searchListFlow = MutableStateFlow(emptyList<ToDoList>())
    val searchListFlow: StateFlow<List<ToDoList>>
        get() = _searchListFlow

    fun addToDoList(toDoList: ToDoList) = viewModelScope.launch {
        repo.createToDoList(toDoList)
    }

    fun deleteToDoList(id: String) = viewModelScope.launch {
        repo.deleteToDoList(id)
    }

    fun search(value: String) {
        viewModelScope.launch {
            repo.search(value).collect {
                _searchListFlow.emit(it)
            }
        }
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

}

