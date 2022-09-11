package com.example.todo.presentation.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todo.domian.model.ToDoStatus
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
            onSwipeToDelete = {
                viewModel.deleteToDoTask(it.id)
            },
            onBackClicked = { controller.popBackStack() },
            onCheckItemClick = {
                viewModel.updateToDoTask(
                    task = it.copy(
                        status = if (it.status == ToDoStatus.COMPLETE)
                            ToDoStatus.IN_PROGRESS else ToDoStatus.COMPLETE
                    )
                )
            },
            onAddToDoItemClick = { title, note, duDate, listId ->
                viewModel.addToDoTask(title, note, duDate, listId)
            }
        )
    }
}

