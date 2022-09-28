package com.example.todo.presentation.list.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.example.todo.domian.model.ToDoList
import com.example.todo.domian.model.ToDoTask
import com.example.todo.presentation.theme.MediumPadding
import kotlinx.coroutines.launch


@OptIn(
    ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class,
    ExperimentalComposeUiApi::class
)
@Composable
fun ListContent(
    toDoList: ToDoList,
    onUpdateToDoTaskClick: (ToDoTask) -> Unit,
    onUpdateToDoListClick: (ToDoList) -> Unit,
    onSwipeToDelete: (ToDoTask) -> Unit,
    onCheckItemClick: (ToDoTask) -> Unit,
    onAddToDoItemClick: (task: ToDoTask, listId: String) -> Unit,
    onBackClicked: () -> Unit,
) {
    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    val keyboard = LocalSoftwareKeyboardController.current
    var currentTask: ToDoTask? by remember { mutableStateOf(null) }
    var showListDialog by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = bottomSheetState.currentValue) {
        if (bottomSheetState.currentValue == ModalBottomSheetValue.Hidden)
            keyboard?.hide()
    }

    currentTask?.let {
        TaskEditDialog(
            task = it,
            onSave = { editToDoTask ->
                currentTask = null
                onUpdateToDoTaskClick(editToDoTask)
            },
            onCancel = {
                currentTask = null
            }
        )
    }

    if (showListDialog) {
        ListEditDialog(
            toDoList = toDoList,
            onSave = { editToDoList ->
                showListDialog = false
                onUpdateToDoListClick(editToDoList)
            },
            onCancel = {
                showListDialog = false
            }
        )
    }

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = {
            ToDoBottomSheet { task ->
                onAddToDoItemClick(task, toDoList.id)
                coroutineScope.launch { bottomSheetState.hide() }
            }
        },
    ) {
        Scaffold(
            topBar = {
                ListScreenTopBar(toDoList, onBackClicked) {
                    showListDialog = true
                }
            },
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    coroutineScope.launch { bottomSheetState.show() }
                }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                }
            }
        ) {
            ToDoColumn(
                list = toDoList.tasks,
                taskColor = toDoList.color,
                onSwipeToDelete = onSwipeToDelete,
                onTaskItemClick = { toToTask ->
                    currentTask = toToTask
                },
                onCheckItemClick = onCheckItemClick,
                modifier = Modifier
                    .padding(it)
                    .padding(horizontal = MediumPadding)
            )
        }
    }
}



