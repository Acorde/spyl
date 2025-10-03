package com.moe.spyl.presentation.flow.core.utils

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberBaseNavController(): NavHostController {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    LaunchedEffect(key1 = currentRoute, block = {
        Log.d("BaseFragment", "Navigation Component Name:$currentRoute")
        //Print Component name in PascalCase
        currentRoute?.split('_')
            ?.joinToString("") { it.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() } }
            ?.let {
                Log.d("BaseFragment", "Navigation Component PascalCase Name:$it")
            }
    })
    return navController
}
