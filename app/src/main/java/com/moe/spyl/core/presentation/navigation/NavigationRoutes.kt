package com.moe.spyl.core.presentation.navigation

sealed interface NavigationRoutes {}

sealed interface MainNavigationRoutes : NavigationRoutes {
    data object QRScanner : MainNavigationRoutes
    data object Login : MainNavigationRoutes
    data object Chat : MainNavigationRoutes
    data object Settings : MainNavigationRoutes
    data object Profile : MainNavigationRoutes
    data object Files : MainNavigationRoutes
}

sealed interface ChatNavigationRoutes : NavigationRoutes {
    data object Initial : ChatNavigationRoutes
    data object SelectLanguage : ChatNavigationRoutes
    data object Chat : ChatNavigationRoutes
}

sealed interface SettingsNavigationRoutes : NavigationRoutes {
    data object Employee : SettingsNavigationRoutes
    data object AddNewEmployee : SettingsNavigationRoutes
    data object CompanyData : SettingsNavigationRoutes
}