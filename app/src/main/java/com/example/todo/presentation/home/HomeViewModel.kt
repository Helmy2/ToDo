package com.example.todo.presentation.home

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.domian.model.ToDoList
import com.example.todo.domian.repository.ToDoRepo
import com.example.todo.presentation.util.dummyRandomCategory
import com.example.todo.presentation.util.dummyRandomList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "HomeViewModel"

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: ToDoRepo
) : ViewModel() {
    val groupState: MutableState<List<ToDoList>> = mutableStateOf(emptyList())
    val toDoListState: MutableState<List<ToDoList>> = mutableStateOf(emptyList())

    init {
        viewModelScope.launch {
            updateState()
        }
        groupState.value = dummyRandomCategory()
    }

    fun addToDoList(it: ToDoList) = viewModelScope.launch {
        repo.createToDoList(it)
        updateState()
    }

    fun deleteToDoList(it: ToDoList) = viewModelScope.launch {
        repo.deleteToDoList(it.id)
        updateState()
    }

    private suspend fun updateState() {
        val result = repo.getAllToDoLists()
        if (result.isSuccess) {
            toDoListState.value = result.getOrThrow()
            Log.d(TAG, "updateState: ${toDoListState.value.size}")
        }
    }
}

