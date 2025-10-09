package com.moe.spyl.presentation.flow.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Devices.TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moe.spyl.data.core.domain.User
import com.moe.spyl.data.core.domain.UserType
import com.moe.spyl.presentation.flow.chat.models.ChatMessage
import com.moe.spyl.presentation.flow.core.components.SideNavRail
import com.moe.spyl.presentation.flow.core.models.NavItemType
import com.moe.spyl.presentation.flow.login.models.LoginState
import com.moe.spyl.presentation.flow.login.preview.LoginStatePreviewParameterProvider
import com.moe.spyl.ui.theme.SpylTheme

@Composable
fun ChatLandScapeView(
    modifier: Modifier = Modifier,
    messagesList: List<ChatMessage>,
    isLoading: Boolean = false,
) {

    val employeeLazyColumState = rememberLazyListState()
    val customerLazyColumState = rememberLazyListState()

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE5E7EB))
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp),

        ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .clip(RoundedCornerShape(24.dp))
                .background(Color.White)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 12.dp, vertical = 5.dp),
                state = employeeLazyColumState,

                ) {
                itemsIndexed(
                    items = messagesList,
                    key = { index, item -> item.id }
                ) { index, message ->
                    ChatMessagesWrapperView(
                        message = message,
                        isLastIndex = index == messagesList.lastIndex
                    )
                }
            }

            ChatEmployeeActionButtonsView(
                modifier = Modifier
                    .align(alignment = Alignment.BottomCenter)
                    .padding(20.dp)
            )
        }

        // Opposite side — flip the entire column
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .clip(RoundedCornerShape(24.dp))
                .background(Color.White)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 12.dp, vertical = 5.dp)
                    .graphicsLayer { rotationZ = 180f },  // flip list for the opposite viewer
                state = customerLazyColumState,
            ) {
                itemsIndexed(
                    items = messagesList,
                    key = { index, item -> item.id }
                ) { index, message ->
                    ChatMessagesWrapperView(
                        message = message,
                        isLastIndex = index == messagesList.lastIndex
                    )
                }
            }
            ChatPulseAnimatedButton(
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .padding(20.dp),
                size = 60.dp,
                waves = 3,
                waveMaxExtra = 25.dp,
                ringStyle = false // set false for filled pulses
            )
//            ChatCustomerActionButtonsView(
//                modifier = Modifier
//                    .align(alignment = Alignment.TopCenter)
//                    .padding(20.dp)
//            )
        }
    }
}

@Preview(name = "Tablet - Landscape", device = TABLET, showSystemUi = true)
@Composable
private fun ChatLandScapeViewPreview(
    @PreviewParameter(LoginStatePreviewParameterProvider::class) state: LoginState
) {
    SpylTheme {
        var code by remember { mutableStateOf(CharArray(4) { ' ' }) }
        val focusRequesters = remember { (1..4).map { FocusRequester() } }
        Row(modifier = Modifier.fillMaxSize()) {
            val widthClass = WindowWidthSizeClass.Expanded
            if (widthClass == WindowWidthSizeClass.Medium || widthClass == WindowWidthSizeClass.Expanded) {

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
                    //Spacer(Modifier.width(16.dp))
            }

            ChatLandScapeView(
                messagesList = listOf(
                    ChatMessage.EmployeeMessage(text = "Hi, Welcome to 4 Season Hotel"),
                    ChatMessage.CustomerMessage(text = "הי אני רוצה"),
                    ChatMessage.EmployeeMessage(text = "May I have your id number"),
                    ChatMessage.CustomerMessage(text = "מספר הת״ז שךי הוא 111111111"),

                    )
            )
        }
    }
}