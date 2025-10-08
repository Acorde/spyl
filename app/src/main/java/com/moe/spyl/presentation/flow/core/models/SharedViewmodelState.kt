package com.moe.spyl.presentation.flow.core.models

import com.moe.spyl.presentation.flow.core.navigation.MainNavigationRoutes
import com.moe.spyl.presentation.flow.core.navigation.NavigationRoutes

data class SharedViewmodelState(
    val test : String = "",
    val initialRoute : NavigationRoutes = MainNavigationRoutes.QRScanner
)
