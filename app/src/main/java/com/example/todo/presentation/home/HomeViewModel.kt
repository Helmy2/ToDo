package com.example.todo.presentation.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.todo.domian.model.ToDoList
import com.example.todo.presentation.util.dummyRandomCategory
import com.example.todo.presentation.util.dummyRandomList

class HomeViewModel : ViewModel() {
    val groupState: MutableState<List<ToDoList>> = mutableStateOf(emptyList())
    val toDoListState: MutableState<List<ToDoList>> = mutableStateOf(emptyList())

    init {
        groupState.value = dummyRandomCategory()
        toDoListState.value = dummyRandomList()
    }
}

