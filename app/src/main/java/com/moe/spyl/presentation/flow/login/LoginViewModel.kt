package com.moe.spyl.presentation.flow.login

import androidx.lifecycle.ViewModel
import com.moe.spyl.presentation.flow.login.models.LoginCompanyCodeEvent
import com.moe.spyl.presentation.flow.login.models.LoginEvent
import com.moe.spyl.presentation.flow.login.models.LoginPasswordEvent
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
            is LoginCompanyCodeEvent.OnChangeFieldFocused -> updateFocusIndex(event.index)
            is LoginCompanyCodeEvent.OnEnterNumber -> enterNumber(
                number = event.number, index = event.index
            )

            LoginCompanyCodeEvent.OnKeyboardBack -> handleOnKeyboardBack()
            is LoginPasswordEvent.OnPasswordChanged -> setPassword(event.password)
        }
    }

    private fun handleOnKeyboardBack() {
        val previousIndex = getPreviousFocusedIndex(state.value.focusIndex)

        _state.update {
            it.copy(
                code = it.code.mapIndexed { index, number ->
                    if (index == previousIndex) {
                        null
                    } else {
                        number
                    }
                }, focusIndex = previousIndex
            )
        }
    }

    private fun getPreviousFocusedIndex(currentIndex: Int?): Int? {
        return currentIndex?.minus(1)?.coerceAtLeast(0)
    }

    private fun getFirstEmptyFieldIndexAfterFocusedIndex(
        code: List<Int?>, currentFocusedIndex: Int
    ): Int {
        code.forEachIndexed { index, number ->
            if (index <= currentFocusedIndex) {
                return@forEachIndexed
            }

            if (number == null) {
                return index
            }
        }
        return currentFocusedIndex

    }

    private fun enterNumber(number: Int?, index: Int) {
        val newCode = state.value.code.mapIndexed { currentIndex, currentNumber ->
            if (currentIndex == index) {
                number
            } else {
                currentNumber
            }
        }

        val sawNumberRemoved = number == null
        _state.update {
            it.copy(
                code = newCode,
                focusIndex = if (sawNumberRemoved || it.code.getOrNull(index) != null) {
                    it.focusIndex
                } else {
                    getNextFocusedTextFieldIndex(
                        currentCode = it.code, currentFocusedIndex = it.focusIndex
                    )
                }
            )
        }

        if (newCode.none() { it == null }) {
            newCode.joinToString("")
            //TODO
        }

    }

    private fun getNextFocusedTextFieldIndex(
        currentCode: List<Int?>, currentFocusedIndex: Int?
    ): Int? {
        if (currentFocusedIndex == null) {
            return null
        }
        if (currentFocusedIndex == 3) {
            return currentFocusedIndex
        }

        return getFirstEmptyFieldIndexAfterFocusedIndex(
            code = currentCode, currentFocusedIndex = currentFocusedIndex
        )
    }

    private fun updateFocusIndex(index: Int) {
        _state.update {
            it.copy(
                focusIndex = index
            )
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