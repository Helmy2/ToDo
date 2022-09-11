package com.example.todo.presentation.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import javax.inject.Inject

@Composable
fun ListRoute(
    id: String,
    controller: NavController,
    viewModel: ListViewModel = hiltViewModel()
) {

    val toDoList by viewModel.getToDoList(id).collectAsState(initial = null)

    toDoList?.let {
        ListScreen(
            toDoList = it,
            onItemClick = {},
            onSwipeToDelete = {},
            onBackClicked = { controller.popBackStack() },
            onCheckItemClick = {},
            onAddToDoItemClick = { title, note, duDate, listId ->
                viewModel.addToDoTask( title, note, duDate, listId)
            }
        )
    }
}

