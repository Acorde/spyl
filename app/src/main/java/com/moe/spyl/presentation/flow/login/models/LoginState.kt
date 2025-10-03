package com.moe.spyl.presentation.flow.login.models

data class LoginState(
    val companyCode: String = "",
    val password: String = "",
    val isCanContinue: Boolean = false
)
