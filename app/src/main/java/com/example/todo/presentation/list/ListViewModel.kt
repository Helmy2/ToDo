package com.example.todo.presentation.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.domian.model.ToDoList
import com.example.todo.domian.repository.ToDoRepo
import com.example.todo.presentation.util.dummyRandomList
import kotlinx.coroutines.launch

class ListViewModel(
    private val repo: ToDoRepo
) : ViewModel() {
    private val _listState = mutableStateOf<ToDoList?>(null)
    val listState: State<ToDoList?> = _listState

    fun getToDoList(id: String) {
        viewModelScope.launch {
            val result = repo.getToDoList(id)
            if (result.isSuccess)
                _listState.value = result.getOrThrow()
        }
    }
}