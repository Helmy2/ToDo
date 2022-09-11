package com.example.todo.domian.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AllInbox
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Today
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Category(
    val name: String,
    val icon: ImageVector,
    val color: ToDoColor,
    val list: List<ToDoTask>
) {
    data class Today(val toDOTaskList: List<ToDoTask>) :
        Category("Today", Icons.Default.Today, ToDoColor.BLUE, toDOTaskList)

    data class Scheduled(val toDOTaskList: List<ToDoTask>) :
        Category("Scheduled", Icons.Default.CalendarMonth, ToDoColor.RED, toDOTaskList)

    data class All(val toDOTaskList: List<ToDoTask>) :
        Category("All", Icons.Default.AllInbox, ToDoColor.BROWN, toDOTaskList)
}
