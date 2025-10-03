package com.moe.spyl.presentation.flow.login

import androidx.lifecycle.ViewModel
import com.moe.spyl.presentation.flow.login.models.LoginCompanyCodeEvent
import com.moe.spyl.presentation.flow.login.models.LoginEvent
import com.moe.spyl.presentation.flow.login.models.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()


    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginCompanyCodeEvent.Backspace -> {
                setPassword(reduceCompanyCode(state.value.password, event))
            }

            is LoginCompanyCodeEvent.DigitChanged -> {
                setPassword(reduceCompanyCode(state.value.password, event))
            }

            is LoginCompanyCodeEvent.Paste -> {
                setPassword(reduceCompanyCode(state.value.password, event))
            }
        }
    }

    private fun setPassword(password: String) {
        _state.update {
            it.copy(
                password = password
            )
        }
    }
}