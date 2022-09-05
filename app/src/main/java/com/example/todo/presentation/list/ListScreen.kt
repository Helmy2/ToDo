package com.example.todo.presentation.list

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.todo.domian.model.ToDoList
import com.example.todo.domian.model.ToDoTask
import com.example.todo.presentation.list.components.BottomSheet
import com.example.todo.presentation.list.components.ListScreenTopBar
import com.example.todo.presentation.list.components.TaskList
import com.example.todo.presentation.theme.LargePadding
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    toDoList: ToDoList,
    onItemClick: (ToDoTask) -> Unit,
    onSwipeToDelete: (ToDoTask) -> Unit,
    onCheckItemClick: (ToDoTask) -> Unit,
    onAddToDoItemClick: () -> Unit,
    onBackClicked: () -> Unit,
) {
    val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetState = state,
        sheetContent = {
            BottomSheet {
                scope.launch { state.hide() }
            }
        },
    ) {
        Scaffold(
            topBar = { ListScreenTopBar(onBackClicked, toDoList) },
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    scope.launch { state.show() }
                }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .padding(horizontal = LargePadding)
            ) {
                TaskList(
                    list = toDoList.tasks,
                    taskColor = toDoList.color,
                    onSwipeToDelete = onSwipeToDelete,
                    onTaskItemClick = onItemClick,
                    onCheckItemClick = onCheckItemClick
                )
            }
        }
    }
}

