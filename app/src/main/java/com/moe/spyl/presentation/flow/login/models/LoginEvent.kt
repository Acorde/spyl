package com.moe.spyl.presentation.flow.login.models

sealed interface LoginEvent {

}

sealed interface LoginCompanyCodeEvent : LoginEvent {

    data class OnEnterNumber(val number: Int?, val index : Int) : LoginCompanyCodeEvent
    data class OnChangeFieldFocused(val index : Int) : LoginCompanyCodeEvent
    data object OnKeyboardBack : LoginCompanyCodeEvent
}

sealed interface LoginPasswordEvent : LoginEvent {
    data class OnPasswordChanged(val password: String) : LoginPasswordEvent

}