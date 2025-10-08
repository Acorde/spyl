package com.moe.spyl.presentation.ui.ext

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

fun <T : Any> NavHostController.navigateClearStack(route: T) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) {
            inclusive = true
            saveState = false
        }
        launchSingleTop = true     // avoid duplicates if re-triggered
        restoreState = false
    }
}