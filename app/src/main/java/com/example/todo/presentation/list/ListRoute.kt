package com.example.todo.presentation.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.todo.domian.model.ToDoColor
import com.example.todo.domian.model.ToDoList
import com.example.todo.domian.model.ToDoTask
import org.koin.androidx.compose.getViewModel

@Composable
fun ListRoute(
    toDoList: ToDoList?,
    viewModel: ListViewModel = getViewModel()
) {
    ListScreen(
        toDoList = toDoList!!,
        onItemClick = {},
        onSwipeToDelete = {},
        onBackClicked = {},
        onCheckItemClick = {},
        onAddToDoItemClick = {}
    )
}

