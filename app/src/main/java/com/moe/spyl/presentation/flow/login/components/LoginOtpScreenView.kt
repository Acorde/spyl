package com.moe.spyl.presentation.flow.login.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.moe.spyl.presentation.flow.login.models.LoginCompanyCodeEvent
import com.moe.spyl.presentation.flow.login.models.LoginEvent
import com.moe.spyl.presentation.flow.login.models.LoginState
import com.moe.spyl.presentation.flow.login.preview.LoginStatePreviewParameterProvider
import com.moe.spyl.ui.theme.SpylTheme

@Composable
fun LoginOtpScreenView(
    modifier: Modifier = Modifier,
    focusRequesters: List<FocusRequester> = emptyList(),
    state: LoginState = LoginState(),
    onEvent: (LoginEvent) -> Unit = {}
) {
    Column(modifier = modifier.wrapContentWidth()) {
        Text(
            modifier = Modifier.align(Alignment.Start),
            text = "Company Code",
            style = MaterialTheme.typography.labelMedium.copy(color = Color(0xFF6B7280))
        )
        Spacer(Modifier.height(8.dp))
        Row(
            modifier = modifier.wrapContentWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                12.dp,
                alignment = Alignment.CenterHorizontally
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            state.code.forEachIndexed { index, number ->
                SingleOtpView(
                    modifier = Modifier
                        //.weight(1f)
                        //  .aspectRatio(1f)
                        .size(width = 60.dp, height = 60.dp),
                    number = number,
                    focusRequester = focusRequesters[index],
                    onFocusChanged = { isFocused ->
                        if (isFocused) {
                            onEvent(LoginCompanyCodeEvent.OnChangeFieldFocused(index))

                        }

                    }, onNumberChange = {
                        onEvent(LoginCompanyCodeEvent.OnEnterNumber(it, index))
                    },
                    onKeyboardBack = {
                        onEvent(LoginCompanyCodeEvent.OnKeyboardBack)
                    }

                )
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun LoginOtpScreenViewPreview(
    @PreviewParameter(LoginStatePreviewParameterProvider::class)
    state: LoginState
) {
    val focusRequesters = remember { (1..4).map { FocusRequester() } }
    SpylTheme {
        LoginOtpScreenView(
            state = state,
            focusRequesters = focusRequesters,
        )
    }
}
