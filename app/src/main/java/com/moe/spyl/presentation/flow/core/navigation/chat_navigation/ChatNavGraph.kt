package com.moe.spyl.presentation.flow.core.navigation.chat_navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.moe.spyl.presentation.flow.chat.ChatView
import com.moe.spyl.presentation.flow.chat.ChatViewModel
import com.moe.spyl.presentation.flow.core.navigation.ChatNavigationRoutes

fun NavGraphBuilder.chatNavGraph(
    navController: NavHostController
) {

    composable<ChatNavigationRoutes.Initial> {
        //TODO,,,
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                modifier = Modifier.align(alignment = Alignment.Center),
                text = "Initial View"
            )
        }
    }
    composable<ChatNavigationRoutes.SelectLanguage> {
        //TODO,,,
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                modifier = Modifier.align(alignment = Alignment.Center),
                text = "SelectLanguage View"
            )
        }
    }


    composable<ChatNavigationRoutes.Chat> {
        val viewModel = hiltViewModel<ChatViewModel>()
        val state = viewModel.state.collectAsStateWithLifecycle()

        ChatView()

    }
}