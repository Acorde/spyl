package com.moe.spyl.presentation.flow.login.models

data class LoginState(
    val companyCode: String = "",
    val password: String = "",
    val code : List<Int?> = (1..4).map{ null },
    val focusIndex : Int? = null,
    val isValid : Boolean? = null,
    val isCanContinue: Boolean = false
)
