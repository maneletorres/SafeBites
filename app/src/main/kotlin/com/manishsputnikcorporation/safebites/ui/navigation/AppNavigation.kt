package com.manishsputnikcorporation.safebites.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController, startDestination = "toDo", modifier = modifier
    ) {
        composable(route = "toDO") {
            // TODO:
        }
    }
}