package com.moe.spyl.presentation.flow.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Mic
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.moe.spyl.presentation.flow.login.models.LoginState
import com.moe.spyl.presentation.flow.login.preview.LoginStatePreviewParameterProvider
import com.moe.spyl.ui.theme.SpylTheme

@Composable
fun ChatCustomerActionButtonsView(
    modifier: Modifier = Modifier
) {
    Icon(
        modifier = modifier
            .clip(CircleShape)
            .background(Color.Black)
            .padding(10.dp)
            .rotate(180f),
        imageVector = Icons.Rounded.Mic,
        contentDescription = null,
        tint = Color.White
    )
}


@Preview(showBackground = true)
@Composable
private fun CChatCustomerActionButtonsViewPreview(
    @PreviewParameter(LoginStatePreviewParameterProvider::class) state: LoginState
) {
    SpylTheme {
        ChatCustomerActionButtonsView()
    }
}