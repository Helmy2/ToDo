package com.example.todo.presentation.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todo.domian.model.ToDoColor
import com.example.todo.domian.model.ToDoList
import com.example.todo.domian.model.ToDoTask

@Composable
fun ToDoListField(
    list: List<ToDoList>,
    onAddTaskItemClick: (ToDoList) -> Unit,
    onDeleteItemClick: (ToDoList) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = "My Lists", modifier = Modifier.padding(bottom = 8.dp))
        LazyColumn {
            items(list) {
                ListItem(
                    toDoList = it,
                    onSwipeToDelete = {onDeleteItemClick(it)},
                    onTaskItemClick = {onAddTaskItemClick(it)},
                )
            }
        }
    }
}