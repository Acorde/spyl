package com.moe.spyl.presentation.flow.chat.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.moe.spyl.presentation.flow.chat.models.ChatMessage
import com.moe.spyl.presentation.flow.core.utils.ui.InitialAnimationWrapper

@Composable
fun ChatMessagesWrapperView(
    modifier: Modifier = Modifier,
    message: ChatMessage,
    horizontalPadding: Dp = 0.dp,
    isLastIndex: Boolean = true
) {


    val sharedModifier = Modifier
        .fillMaxWidth()
        .padding(top = 8.dp, bottom = 8.dp)
        .padding(horizontal = horizontalPadding)


    InitialAnimationWrapper(
        modifier = modifier,
        key = message.id
    ) {
        when (message) {
            is ChatMessage.EmployeeMessage -> ChatEmployeeAnswerView(
                modifier = sharedModifier,
                message = message,

                )

            is ChatMessage.CustomerMessage -> ChatCustomerAnswerView(
                modifier = sharedModifier,
                message = message,

                )


        }
    }

}