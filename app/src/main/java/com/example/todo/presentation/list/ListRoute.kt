package com.example.todo.presentation.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun ListRoute(
    id: String,
    controller: NavController,
    viewModel: ListViewModel = hiltViewModel()
) {
    LaunchedEffect(null) {
        viewModel.getToDoList(id)
    }

    val toDoList = viewModel.listState.value

    toDoList?.let {
        ListScreen(
            toDoList = toDoList,
            onItemClick = {},
            onSwipeToDelete = {},
            onBackClicked = { controller.popBackStack() },
            onCheckItemClick = {},
            onAddToDoItemClick = {}
        )
    }
}

