package com.example.todo.presentation.navigation

sealed class Screens(val rute: String, val argument0: String = "id") {
    val routeWithArgument: String = "$rute?$argument0={$argument0}"
    fun navRuteWithArgument(id: String): String {
        return "$rute?$argument0=$id"
    }

    object HomeScreen : Screens("home_rute")
    object ListScreen : Screens("list_rute", "list_id")
}
