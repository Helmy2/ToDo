package com.example.todo.presentation.navigation

sealed class Rutes(val value: String) {
    object HomeRute : Rutes("home_rutes")
    object ListRute : Rutes("list_rutes")
}
