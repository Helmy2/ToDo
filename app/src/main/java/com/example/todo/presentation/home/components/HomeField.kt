package com.example.todo.presentation.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Task
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todo.domian.model.ToDoGroup
import com.example.todo.domian.model.ToDoList
import com.example.todo.presentation.util.FABItem
import com.example.todo.presentation.util.MultiFloatingActionButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeField(
    lists: List<ToDoList>,
    groups: List<ToDoGroup>,
    onProfileClicked: () -> Unit,
    onAddListClicked: () -> Unit,
    onAddTaskClicked: () -> Unit,
    onAddTaskItemClick: (ToDoList) -> Unit,
    onDeleteTaskItemClick: (ToDoList) -> Unit,
    onListItemClick: (ToDoGroup) -> Unit,
    onSearchClick: () -> Unit,
) {

    Scaffold(
        topBar = {
            HomeTopBar(
                onImageClick = onProfileClicked,
                onSearchClick,
                modifier = Modifier.padding(top = 24.dp, start = 24.dp, end = 24.dp)
            )
        },
        floatingActionButton = {
            MultiFloatingActionButton(
                list = listOf(
                    FABItem("Add Task", Icons.Default.Task) {
                        onAddTaskClicked()
                    },
                    FABItem("Add List", Icons.Default.List) {
                        onAddListClicked()
                    })
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .padding(it)
        ) {
            CategoryListField(groups, onListItemClick = onListItemClick)
            ToDoListField(
                list = lists,
                onAddTaskItemClick = onAddTaskItemClick,
                onDeleteItemClick = onDeleteTaskItemClick,
            )
        }
    }
}


