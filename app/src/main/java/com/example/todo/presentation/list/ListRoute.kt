package com.example.todo.presentation.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import org.koin.androidx.compose.getViewModel

@Composable
fun ListRoute(
    id: String,
    controller: NavController,
    viewModel: ListViewModel = getViewModel()
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

