package com.example.todo.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.todo.domian.model.ToDoColor
import com.example.todo.domian.model.ToDoList
import com.example.todo.domian.model.ToDoTask
import com.example.todo.presentation.theme.LargePadding
import com.example.todo.presentation.theme.MediumPadding
import com.example.todo.presentation.theme.SmallPadding
import com.example.todo.presentation.theme.ToDoTheme
import com.example.todo.presentation.util.dummyList


@OptIn(
    ExperimentalMaterial3Api::class
)
@Composable
fun HomeContent(
    lists: List<ToDoList>,
    onAddListClicked: (ToDoList) -> Unit,
    onSearchClick: (String) -> Unit,
    onListItemClick: (String) -> Unit,
    onDeleteListItemClick: (String) -> Unit,
    searchList: List<ToDoList>,
    onSwipeToDelete: (ToDoTask) -> Unit,
    onCheckItemClick: (task: ToDoTask, listId: String) -> Unit,
    onUpdateToDoTaskClick: (ToDoTask, listId: String) -> Unit
) {

    var showListDialog by rememberSaveable {
        mutableStateOf(false)
    }

    var search by rememberSaveable {
        mutableStateOf(false)
    }

    if (showListDialog)
        ListDialog(
            ToDoColor.BLUE,
            onCancel = { showListDialog = false },
            onSave = { toDoList ->
                onAddListClicked(toDoList)
                showListDialog = false
            }
        )

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showListDialog = true }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        },
    ) {
        Column(
            modifier = Modifier
                .padding(top = LargePadding)
                .padding(it)
                .padding(horizontal = MediumPadding),
            verticalArrangement = Arrangement.spacedBy(SmallPadding)
        ) {
            SearchView(
                onTextUpdate = {
                    onSearchClick(it)
                    search = it.isNotEmpty()
                },
            )
            if (search) {
                SearchColumn(
                    list = searchList,
                    onSwipeToDelete = onSwipeToDelete,
                    onTaskItemClick = onUpdateToDoTaskClick,
                    onCheckItemClick = onCheckItemClick
                )
            } else {
                Text(
                    text = "My Lists",
                    modifier = Modifier.padding(horizontal = SmallPadding),
                    style = MaterialTheme.typography.titleMedium
                )
                ToDoListColumn(
                    list = lists,
                    onListItemClick = onListItemClick,
                    onDeleteItemClick = onDeleteListItemClick,
                )
            }
        }
    }
}


@Preview
@Composable
fun HomeContextPrev() {
    ToDoTheme {
        HomeContent(
            lists = dummyList(),
            onAddListClicked = { },
            onSearchClick = {},
            onListItemClick = {},
            onDeleteListItemClick = {},
            searchList = emptyList(),
            onSwipeToDelete = { },
            onCheckItemClick = { _, _ -> },
            onUpdateToDoTaskClick = {_,_->}
        )
    }
}