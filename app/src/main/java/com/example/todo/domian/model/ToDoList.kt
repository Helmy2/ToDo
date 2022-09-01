package com.example.todo.domian.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector
import java.time.LocalDateTime

data class ToDoList(
    val id: String = "",
    val name: String = "",
    val color: ToDoColor = ToDoColor.BLUE,
    val icon: ImageVector = Icons.Default.List,
    val tasks: List<ToDoTask> = listOf(),
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)

