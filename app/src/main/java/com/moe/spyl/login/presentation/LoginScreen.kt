package com.moe.spyl.login.presentation


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices.TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moe.spyl.login.presentation.models.LoginState
import com.moe.spyl.login.presentation.components.LoginCompanyCode
import com.moe.spyl.login.presentation.components.LoginPasswordView
import com.moe.spyl.login.presentation.models.LoginEvent
import com.moe.spyl.login.preview.LoginStatePreviewParameterProvider
import com.moe.spyl.ui.theme.SpylTheme

@Composable
fun LoginScreen(
    state: LoginState,
    onEvent: (LoginEvent) -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Card(
            modifier = Modifier
                .wrapContentSize()
                .padding(16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Color(0xFFE5E7EB))
        ) {
            Column(
                Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Log in",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    modifier = Modifier.wrapContentSize(),
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(20.dp))

                LoginCompanyCode(code = state.companyCode, onEvent = onEvent)

                LoginPasswordView(password = state.password)

                Spacer(Modifier.height(16.dp))

                Button(
                    onClick = { },
                    enabled = state.isCanContinue,
                    modifier = Modifier
                        .wrapContentSize()
                        .height(48.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 50.dp),
                        text = "Continue"
                    )
                }

            }
        }
    }
}

@Preview(name = "Tablet - Landscape", device = TABLET, showSystemUi = true)
@Composable
private fun CoinListScreenPreview(
    @PreviewParameter(LoginStatePreviewParameterProvider::class)
    state: LoginState
) {
    SpylTheme {
        var code by remember { mutableStateOf(CharArray(4) { ' ' }) }
        LoginScreen(state = state)
    }
}