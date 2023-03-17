package com.manishsputnikcorporation.safebites.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.manishsputnikcorporation.safebites.ui.navigation.AppNavigation
import com.manishsputnikcorporation.safebites.ui.theme.SafeBitesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent { SafeBitesApp() }
  }
}

@Composable
fun SafeBitesApp() {
  SafeBitesTheme {
    val navController = rememberNavController()
    AppNavigation(navController)
  }
}
