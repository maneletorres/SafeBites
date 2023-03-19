package com.manishsputnikcorporation.safebites.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.manishsputnikcorporation.safebites.ui.screens.home.HomeScreen
import com.manishsputnikcorporation.safebites.ui.screens.splash.SplashScreen
import com.manishsputnikcorporation.safebites.ui.utils.extensions.navigateSingleTopTo
import timber.log.Timber

@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
  NavHost(navController = navController, startDestination = Home.route, modifier = modifier) {
    composable(route = SplashScreen.route) {
      SplashScreen {
        navController.apply {
          popBackStack()
          navigateSingleTopTo(Home.route)
        }
      }
    }
    composable(route = Home.route) {
      HomeScreen(homeViewModel = hiltViewModel()) { productId ->
        Timber.tag("SafeBites").d("Product ID: $productId")
        // navController.navigateSingleTopTo("goToProductDetail") // TODO:
      }
    }
  }
}
