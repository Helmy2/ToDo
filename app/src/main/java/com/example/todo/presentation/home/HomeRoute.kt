package com.example.todo.presentation.home

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.todo.presentation.navigation.Rutes

private const val TAG = "HomeRoute"

@Composable
fun HomeRoute(
    controller: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val lists = viewModel.toDoListState.value
    val groups = viewModel.groupState.value

    HomeScreen(
        lists = lists,
        categories = groups,
        onDrawerClicked = {},
        onAddListClicked = {
            viewModel.addToDoList(it)
        },
        onListItemClick = {
            controller.navigate(Rutes.ListRute.value + "?id=${it.id}")
        },
        onDeleteListItemClick = {
            viewModel.deleteToDoList(it)
        },
        onCategoryItemClick = {},
        onSearchClick = {}
    )
}


