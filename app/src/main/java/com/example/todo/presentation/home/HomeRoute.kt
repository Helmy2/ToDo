package com.example.todo.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.todo.presentation.navigation.Rutes

private const val TAG = "HomeRoute"

@Composable
fun HomeRoute(
    controller: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val lists by viewModel.toDoListFlow.collectAsState(initial = emptyList())
    val categories by viewModel.categoryFlow.collectAsState(initial = emptyList())

    HomeScreen(
        lists = lists,
        categories = categories,
        onDrawerClicked = {},
        onAddListClicked = { title, color ->
            viewModel.addToDoList(title, color)
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


