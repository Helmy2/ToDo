package com.example.todo.presentation.list

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.todo.domian.model.ToDoList
import com.example.todo.domian.model.ToDoTask
import com.example.todo.presentation.list.components.TaskList
import com.example.todo.presentation.theme.LargePadding
import com.example.todo.presentation.util.BackButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    toDoList: ToDoList,
    onItemClick: (ToDoTask) -> Unit,
    onSwipeToDelete: (ToDoTask) -> Unit,
    onCheckItemClick: (ToDoTask) -> Unit,
    onAddToDoItemClick: () -> Unit,
    onBackClicked: () -> Unit,
) {
    Scaffold(
        topBar = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(LargePadding)
            ) {
                IconButton(onClick = onBackClicked) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = "Back Arrow",
                        tint = MaterialTheme.colorScheme.onBackground,
                    )
                }
                Text(
                    text = toDoList.name,
                    style = MaterialTheme.typography.headlineMedium
                )
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = ""
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddToDoItemClick) {
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
