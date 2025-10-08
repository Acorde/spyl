package com.moe.spyl

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.moe.spyl.data.core.domain.User
import com.moe.spyl.data.core.domain.UserType
import com.moe.spyl.data.core.local.AppWindowSize
import com.moe.spyl.data.core.local.LocalAppWindowSize
import com.moe.spyl.presentation.flow.core.components.SideNavRail
import com.moe.spyl.presentation.flow.core.components.SpylSharedViewModel
import com.moe.spyl.presentation.flow.core.models.NavItemType
import com.moe.spyl.presentation.flow.core.navigation.AppNavGraph
import com.moe.spyl.presentation.flow.core.navigation.MainNavigationRoutes
import com.moe.spyl.presentation.flow.core.utils.rememberBaseNavController
import com.moe.spyl.ui.theme.SpylTheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {


            val windowSizeClass = calculateWindowSizeClass(this)
            val sharedViewModel = hiltViewModel<SpylSharedViewModel>()

            CompositionLocalProvider(
                LocalAppWindowSize provides AppWindowSize(
                    width = windowSizeClass.widthSizeClass,
                    height = windowSizeClass.heightSizeClass
                )
            ) {

                SpylTheme {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        AppRoot(
                            widthClass = windowSizeClass.widthSizeClass,
                            modifier = Modifier.padding(innerPadding),
                            sharedViewModel = sharedViewModel
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AppRoot(
    widthClass: WindowWidthSizeClass,
    modifier: Modifier = Modifier,
    sharedViewModel: SpylSharedViewModel = hiltViewModel()
) {

    // 3) Decide when to hide the rail
    val hideRailOnRoutes = setOf(
        MainNavigationRoutes.Login::class.simpleName,
        MainNavigationRoutes.QRScanner::class.simpleName
    )

    // use widthClass to adapt UI (rail vs labels, columns, etc.)
    val navController = rememberBaseNavController()
    // 2) Observe destination without launching a collector
    val backStackEntry = navController.currentBackStackEntryAsState().value
    val currentRoute = backStackEntry?.destination?.route?.substringBefore('?')

    val isTablet =
        widthClass == WindowWidthSizeClass.Medium || widthClass == WindowWidthSizeClass.Expanded


    val showSideNavRail by rememberUpdatedState(isTablet && currentRoute !in hideRailOnRoutes)

//    LaunchedEffect(key1 = navController) {
//        navController.currentBackStackEntryFlow.collect { backStackEntry ->
//            val route = backStackEntry.destination.route
//            showSideNavRail = when {
//                route?.contains(MainNavigationRoutes.Login::class.simpleName!!) == true
//                        || route?.contains(MainNavigationRoutes.QRScanner::class.simpleName!!) == true -> false
//
//                else -> true
//
//            }
//        }
//    }


    Row(modifier = Modifier.fillMaxSize()) {
        if (showSideNavRail) {

            SideNavRail(
                widthClass = widthClass,
                user = User(
                    userType = UserType.RECEPTIONIST,
                    navItemType = NavItemType.getNavItemUserType(UserType.RECEPTIONIST)
                ),
                selectedNavItem = NavItemType.CHAT,
                onSelect = {
                    //TODO...
                }
            )
            Spacer(Modifier.width(16.dp))
        }

        AppNavGraph(
            modifier = modifier.weight(1f),
            navController = navController,
            sharedviewModle = sharedViewModel
        )
    }

}


@Preview(showBackground = true)
@Composable
fun AppRootPreview() {
    SpylTheme {
        AppRoot(widthClass = WindowWidthSizeClass.Compact)

    }
}