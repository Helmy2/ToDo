package com.example.todo.domian.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector


data class ToDoGroup(
    val name: String = "",
    val icon: ImageVector = Icons.Default.List,
    val iconColor: ToDoColor = ToDoColor.PURPLE,
    val lists: List<ToDoList> = listOf(),
)
