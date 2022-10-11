package com.example.todo.presentation.list.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.todo.domian.model.ToDoList
import com.example.todo.domian.model.ToDoTask
import com.example.todo.presentation.theme.MediumPadding


@OptIn(
    ExperimentalMaterial3Api::class,
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
    var currentTask: ToDoTask? by remember { mutableStateOf(null) }
    var showListDialog by remember { mutableStateOf(false) }
    var showTaskDialog by remember { mutableStateOf(false) }

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
    if (showTaskDialog)
        TaskDialog(
            onCancel = { showTaskDialog = false },
            onSave = { task ->
                onAddToDoItemClick(task, toDoList.id)
                showTaskDialog = false
            }
        )

    Scaffold(
        topBar = {
            ListScreenTopBar(toDoList, onBackClicked) {
                showListDialog = true
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                showTaskDialog = true
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




