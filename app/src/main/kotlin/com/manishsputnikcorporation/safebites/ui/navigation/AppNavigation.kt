package com.manishsputnikcorporation.safebites.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.manishsputnikcorporation.safebites.ui.screens.home.HomeScreen
import com.manishsputnikcorporation.safebites.ui.utils.extensions.navigateSingleTopTo

@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
  NavHost(navController = navController, startDestination = Home.route, modifier = modifier) {
    composable(route = Home.route) {
      HomeScreen(homeViewModel = hiltViewModel()) { productId ->
        navController.navigateSingleTopTo("toDo")
      }
    }
  }
}
