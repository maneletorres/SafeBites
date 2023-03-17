package com.manishsputnikcorporation.safebites.ui.utils.extensions

import androidx.navigation.NavHostController

fun NavHostController.navigateSingleTopTo(route: String) =
    navigate(route) { launchSingleTop = true }