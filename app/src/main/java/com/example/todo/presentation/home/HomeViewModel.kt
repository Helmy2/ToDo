package com.example.todo.presentation.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.domian.model.ToDoColor
import com.example.todo.domian.model.ToDoList
import com.example.todo.domian.repository.ToDoRepo
import com.example.todo.presentation.util.dummyRandomCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "HomeViewModel"

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: ToDoRepo,
) : ViewModel() {
    val groupState: MutableState<List<ToDoList>> = mutableStateOf(emptyList())
    val toDoListState by lazy { repo.getAllToDoLists() }

    init {
        groupState.value = dummyRandomCategory()
    }

    fun addToDoList(title: String, color: ToDoColor) = viewModelScope.launch {
        repo.createToDoList(title, color)
    }

    fun deleteToDoList(it: ToDoList) = viewModelScope.launch {
        repo.deleteToDoList(it.id)
    }

}

