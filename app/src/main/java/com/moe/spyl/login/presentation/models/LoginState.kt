package com.moe.spyl.login.presentation.models

data class LoginState(
    val companyCode: String = "",
    val password: String = "",
    val isCanContinue: Boolean = false
)
