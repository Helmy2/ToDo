package com.example.todo.presentation.list.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.todo.domian.model.ToDoColor
import com.example.todo.domian.model.ToDoStatus
import com.example.todo.domian.model.ToDoTask
import com.example.todo.presentation.theme.SmallPadding

@Composable
fun TaskList(
    list: List<ToDoTask>,
    taskColor: ToDoColor,
    onSwipeToDelete: (ToDoTask) -> Unit,
    onTaskItemClick: (ToDoTask) -> Unit,
    onCheckItemClick: (ToDoTask) -> Unit,
    modifier: Modifier = Modifier
) {
    val completedList = remember(list) {
        list.filter { it.status == ToDoStatus.COMPLETE }
    }
    val inProgressList = remember(list) {
        list.filter { it.status == ToDoStatus.IN_PROGRESS }
    }
    LazyColumn(modifier) {
        items(inProgressList) {
            TaskItem(
                task = it,
                taskColor,
                onSwipeToDelete = { onSwipeToDelete(it) },
                onTaskItemClick = { onTaskItemClick(it) },
                onCheckItemClick = {onCheckItemClick(it)}
            )
        }
        item {
            Divider(modifier = Modifier.padding(vertical = SmallPadding))
            Text(text = "Done (${completedList.size})")
        }
        items(completedList) {
            TaskItem(
                task = it,
                taskColor,
                onSwipeToDelete = { onSwipeToDelete(it) },
                onTaskItemClick = { onTaskItemClick(it) },
                onCheckItemClick = {onCheckItemClick(it)}
            )
        }
    }
}