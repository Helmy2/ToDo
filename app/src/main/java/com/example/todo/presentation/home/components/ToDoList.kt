package com.example.todo.presentation.home.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.todo.domian.model.ToDoList

@Composable
fun ToDoList(
    list: List<ToDoList>,
    onListItemClick: (ToDoList) -> Unit,
    onDeleteItemClick: (ToDoList) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier) {
        items(list) {
            ToDoItem(
                toDoList = it,
                onSwipeToDelete = { onDeleteItemClick(it) },
                onItemClick = { onListItemClick(it) },
            )
        }
    }
}