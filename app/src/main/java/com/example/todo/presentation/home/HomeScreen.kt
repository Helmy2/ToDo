package com.example.todo.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.todo.presentation.home.components.HomeField
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = getViewModel()
) {
    val lists = viewModel.toDoListState.value
    val groups = viewModel.groupState.value

    Box(modifier = Modifier.fillMaxSize()) {
        HomeField(
            lists = lists,
            groups = groups,
            onProfileClicked = {},
            onAddListClicked = {},
            onAddTaskClicked = {},
            onAddTaskItemClick = {},
            onDeleteTaskItemClick = {},
            onListItemClick = {},
            onSearchClick = {}
        )
    }
}


