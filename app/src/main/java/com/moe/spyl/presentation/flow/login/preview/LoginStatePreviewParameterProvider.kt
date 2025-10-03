package com.moe.spyl.presentation.flow.login.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.moe.spyl.presentation.flow.login.models.LoginState

class LoginStatePreviewParameterProvider : PreviewParameterProvider<LoginState> {

    override val values: Sequence<LoginState>
        get() = run {

            sequenceOf(
                LoginState()
            )
        }
}