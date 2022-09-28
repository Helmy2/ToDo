package com.example.todo.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.domian.model.ToDoColor
import com.example.todo.domian.model.ToDoList
import com.example.todo.domian.repository.ToDoRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "HomeViewModel"

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: ToDoRepo,
) : ViewModel() {
    val categoryFlow =  repo.getAllToDoCategory()
    val toDoListFlow =  repo.getAllToDoLists()

    fun addToDoList(toDoList: ToDoList) = viewModelScope.launch {
        repo.createToDoList(toDoList)
    }

    fun deleteToDoList(id: String) = viewModelScope.launch {
        repo.deleteToDoList(id)
    }
}

