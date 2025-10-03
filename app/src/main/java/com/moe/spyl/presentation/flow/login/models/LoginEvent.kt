package com.moe.spyl.presentation.flow.login.models

sealed interface LoginEvent {

}

sealed interface LoginCompanyCodeEvent : LoginEvent {
    data class DigitChanged(val index: Int, val text: String) : LoginCompanyCodeEvent
    data class Backspace(val index: Int) : LoginCompanyCodeEvent
    data class Paste(val index: Int, val digits: String) : LoginCompanyCodeEvent
}