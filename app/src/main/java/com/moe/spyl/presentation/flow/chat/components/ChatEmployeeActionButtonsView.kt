package com.moe.spyl.presentation.flow.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Mic
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.moe.spyl.presentation.flow.login.models.LoginState
import com.moe.spyl.presentation.flow.login.preview.LoginStatePreviewParameterProvider
import com.moe.spyl.ui.theme.SpylTheme

@Composable
fun ChatEmployeeActionButtonsView(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.wrapContentSize(),
        horizontalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        Icon(
            modifier = Modifier
                .clip(CircleShape)
                .background(Color.Black)
                .padding(10.dp),
            imageVector = Icons.Rounded.Mic,
            contentDescription = null,
            tint = Color.White
        )

        Icon(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = CircleShape
                )
                .clip(CircleShape)
                .background(Color.Transparent)
                .padding(10.dp),
            imageVector = Icons.Rounded.Close,
            contentDescription = null,
            tint = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ChatEmployeeActionButtonsViewPreview(
    @PreviewParameter(LoginStatePreviewParameterProvider::class) state: LoginState
) {
    SpylTheme {
        ChatEmployeeActionButtonsView()
    }
}