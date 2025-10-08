package com.moe.spyl.presentation.flow.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.moe.spyl.presentation.flow.core.components.SpylSharedViewModel
import com.moe.spyl.presentation.flow.core.utils.rememberBaseNavController
import com.moe.spyl.presentation.flow.login.LoginScreen
import com.moe.spyl.presentation.flow.login.LoginViewModel
import com.moe.spyl.presentation.flow.qr_code.QrCodeView

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberBaseNavController(),
    sharedviewModle: SpylSharedViewModel
) {

    val sharedState = sharedviewModle.state.collectAsStateWithLifecycle()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = sharedState.value.initialRoute,
    ) {
        composable<MainNavigationRoutes.Login> {
            val viewModel = hiltViewModel<LoginViewModel>()
            val state = viewModel.state.collectAsStateWithLifecycle()

            LoginScreen(
                state = state.value,
                onEvent = viewModel::onEvent
            )
        }

        composable<MainNavigationRoutes.QRScanner> {

            QrCodeView()
        }
    }
}