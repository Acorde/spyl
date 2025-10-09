package com.moe.spyl.presentation.flow.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface NavigationRoutes {}

@Serializable
sealed interface MainNavigationRoutes : NavigationRoutes {
    @Serializable
    data object QRScanner : MainNavigationRoutes

    @Serializable
    data object Login : MainNavigationRoutes

    @Serializable
    data object Settings : MainNavigationRoutes

    @Serializable
    data object Profile : MainNavigationRoutes

    @Serializable
    data object Files : MainNavigationRoutes
}

@Serializable
sealed interface ChatNavigationRoutes : NavigationRoutes {
    @Serializable
    data object Initial : ChatNavigationRoutes

    @Serializable
    data object SelectLanguage : ChatNavigationRoutes

    @Serializable
    data object Chat : ChatNavigationRoutes
}

@Serializable
sealed interface SettingsNavigationRoutes : NavigationRoutes {
    @Serializable
    data object Employee : SettingsNavigationRoutes

    @Serializable
    data object AddNewEmployee : SettingsNavigationRoutes

    @Serializable
    data object CompanyData : SettingsNavigationRoutes
}