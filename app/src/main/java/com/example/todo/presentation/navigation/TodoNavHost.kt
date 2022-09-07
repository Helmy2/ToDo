package com.example.todo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todo.presentation.home.HomeRoute
import com.example.todo.presentation.list.ListRoute

private const val TAG = "TodoNavHost"

@Composable
fun TodoNavHost() {
    val controller = rememberNavController()
    NavHost(navController = controller, startDestination = Rutes.HomeRute.value) {
        composable(Rutes.HomeRute.value) {
            HomeRoute(controller)
        }
        composable(
            route = Rutes.ListRute.value + "?id={id}",
        ) {
            val id = it.arguments?.getString("id")
            ListRoute(id ?: "", controller)
        }
    }
}