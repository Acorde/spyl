package com.moe.spyl.login.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.moe.spyl.login.presentation.models.LoginState

class LoginStatePreviewParameterProvider : PreviewParameterProvider<LoginState> {

    override val values: Sequence<LoginState>
        get() = kotlin.run {

            sequenceOf(
                LoginState()
            )
        }
}