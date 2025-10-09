package com.moe.spyl.presentation.flow.chat

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.moe.spyl.data.core.local.LocalAppWindowSize
import com.moe.spyl.presentation.flow.chat.components.ChatLandScapeView
import com.moe.spyl.presentation.flow.chat.components.ChatPortraitView
import com.moe.spyl.presentation.flow.chat.models.ChatMessage

@Composable
fun ChatView(
    modifier: Modifier = Modifier,
    messagesList: List<ChatMessage> = emptyList(),
    isLoading: Boolean = false,
) {
    val appWindow = LocalAppWindowSize.current


    Box(modifier = Modifier.fillMaxSize()) {

        when (appWindow.width) {
            WindowWidthSizeClass.Medium -> ChatPortraitView(
                messagesList = messagesList,
                isLoading = isLoading
            )

            WindowWidthSizeClass.Expanded -> ChatLandScapeView(
                messagesList = messagesList,
                isLoading = isLoading
            )

            else -> {/* phone fallback */
            }
        }

//        LazyColumn(modifier = Modifier.fillMaxSize()) {
//            //List 1
//            items(10) {
//                Text(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .background(Color.Gray)
//                        .padding(16.dp),
//                    text = "Item $it",
//                    fontSize = 25.sp,
//
//                    )
//            }
//
//            //List 2
//            items(10) {
//                Text(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .background(Color.Green)
//                        .padding(16.dp),
//                    text = "Item $it",
//                    fontSize = 25.sp,
//
//                    )
//            }
//        }
    }
}