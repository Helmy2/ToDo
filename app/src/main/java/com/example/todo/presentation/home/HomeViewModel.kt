package com.example.todo.presentation.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.todo.domian.model.ToDoGroup
import com.example.todo.domian.model.ToDoList
import com.example.todo.domian.model.ToDoTask
import com.example.todo.presentation.util.dummyRandomGroup
import com.example.todo.presentation.util.dummyRandomList

class HomeViewModel : ViewModel() {
    val groupState: MutableState<List<ToDoGroup>> = mutableStateOf(emptyList())
    val toDoListState: MutableState<List<ToDoList>> = mutableStateOf(emptyList())

    init {
        groupState.value = dummyRandomGroup()
        toDoListState.value = dummyRandomList()
    }
}

