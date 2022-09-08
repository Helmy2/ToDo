package com.example.todo.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.todo.domian.model.ToDoList
import com.example.todo.presentation.navigation.Rutes
import kotlinx.coroutines.flow.Flow

private const val TAG = "HomeRoute"

@Composable
fun HomeRoute(
    controller: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val lists by viewModel.toDoListState.collectAsState(initial = emptyList())
    val groups = viewModel.groupState.value

    HomeScreen(
        lists = lists,
        categories = groups,
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


