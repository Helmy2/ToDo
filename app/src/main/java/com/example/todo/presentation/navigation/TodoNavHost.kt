package com.example.todo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todo.presentation.home.HomeScreen
import com.example.todo.presentation.list.ListScreen

@Composable
fun TodoNavHost() {
    val controller = rememberNavController()
    NavHost(navController = controller, startDestination = Screens.HomeScreen.rute) {
        composable(Screens.HomeScreen.rute) {
            HomeScreen(controller)
        }
        composable(Screens.ListScreen.routeWithArgument) {
            ListScreen(controller)
        }
    }
}