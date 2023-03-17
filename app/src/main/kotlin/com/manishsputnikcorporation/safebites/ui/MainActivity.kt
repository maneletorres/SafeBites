package com.manishsputnikcorporation.safebites.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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