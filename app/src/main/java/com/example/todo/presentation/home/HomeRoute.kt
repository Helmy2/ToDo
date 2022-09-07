package com.example.todo.presentation.home

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.todo.presentation.navigation.Rutes
import org.koin.androidx.compose.getViewModel

private const val TAG = "HomeRoute"

@Composable
fun HomeRoute(
    controller: NavHostController,
    viewModel: HomeViewModel = getViewModel()
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


