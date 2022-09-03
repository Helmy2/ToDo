package com.example.todo.presentation.home

import androidx.compose.runtime.Composable
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = getViewModel()
) {
    val lists = viewModel.toDoListState.value
    val groups = viewModel.groupState.value

    HomeScreen(
        lists = lists,
        categories = groups,
        onDrawerClicked = {},
        onAddListClicked = {},
        onAddToDoClicked = {},
        onListItemClick = {},
        onDeleteListItemClick = {},
        onCategoryItemClick = {},
        onSearchClick = {}
    )
}


