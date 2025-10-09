package com.moe.spyl.presentation.flow.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.moe.spyl.presentation.flow.chat.ChatView
import com.moe.spyl.presentation.flow.chat.ChatViewModel
import com.moe.spyl.presentation.flow.core.components.SpylSharedViewModel
import com.moe.spyl.presentation.flow.core.utils.rememberBaseNavController
import com.moe.spyl.presentation.flow.login.LoginScreen
import com.moe.spyl.presentation.flow.login.LoginViewModel
import com.moe.spyl.presentation.flow.login.models.LoginCompanyCodeEvent
import com.moe.spyl.presentation.flow.qr_code.QrCodeView
import com.moe.spyl.presentation.flow.qr_code.QrCodeViewModel
import com.moe.spyl.presentation.ui.ext.navigateClearStack

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
            val focusRequesters = remember { (1..4).map { FocusRequester() } }

            val focusManager = LocalFocusManager.current
            val keyboardManager = LocalSoftwareKeyboardController.current


            LaunchedEffect(key1 = state.value.focusIndex) {
                state.value.focusIndex?.let { focusIndex ->
                    focusRequesters.getOrNull(focusIndex)?.requestFocus()
                }

            }

            LaunchedEffect(key1 = state.value.code, key2 = keyboardManager) {
                val allNumbersEntered = state.value.code.none { it == null }
                if (allNumbersEntered) {
                    focusRequesters.forEach {
                        it.freeFocus()
                    }

                    focusManager.clearFocus()
                    keyboardManager?.hide()
                }
            }

            LoginScreen(
                focusRequesters = focusRequesters,
                state = state.value, onEvent = { event ->
                    when (event) {
                        is LoginCompanyCodeEvent.OnEnterNumber -> {
                            if (event.number != null) {
                                focusRequesters[event.index].freeFocus()
                            }
                        }

                        else -> Unit
                    }

                    viewModel.onEvent(event)
                })
        }

        composable<MainNavigationRoutes.QRScanner> {

            val viewModel = hiltViewModel<QrCodeViewModel>()
            val state = viewModel.state.collectAsStateWithLifecycle()

            LaunchedEffect(state.value.navigationRoute) {
                state.value.navigationRoute?.let { route ->
                    navController.navigateClearStack(route = route)
                }
            }

            QrCodeView(
                state = state.value, onEvent = viewModel::onEvent
            )
        }

        composable<MainNavigationRoutes.Chat> {
            val viewModel = hiltViewModel<ChatViewModel>()
            val state = viewModel.state.collectAsStateWithLifecycle()

            ChatView()

        }
    }
}