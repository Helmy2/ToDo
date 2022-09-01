package com.example.todo.domian.model

import androidx.compose.ui.graphics.Color
import com.example.todo.presentation.theme.*

enum class ToDoColor {
    BLUE,
    RED,
    GREEN,
    YELLOW,
    ORANGE,
    PURPLE,
    BROWN
}

fun ToDoColor.color(): Color =
    when (this) {
        ToDoColor.BLUE -> ListBlue
        ToDoColor.RED -> ListRed
        ToDoColor.GREEN -> ListGreen
        ToDoColor.YELLOW -> ListYellow
        ToDoColor.ORANGE -> ListOrange
        ToDoColor.PURPLE -> ListPurple
        ToDoColor.BROWN -> ListBrown
    }
