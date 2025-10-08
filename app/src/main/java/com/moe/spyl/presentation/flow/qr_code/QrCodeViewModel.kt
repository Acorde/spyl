package com.moe.spyl.presentation.flow.qr_code

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moe.spyl.presentation.flow.core.navigation.MainNavigationRoutes
import com.moe.spyl.presentation.flow.core.navigation.NavigationRoutes
import com.moe.spyl.presentation.flow.login.QrCodeEvent
import com.moe.spyl.presentation.flow.qr_code.models.QrCodeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QrCodeViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(QrCodeState())
    val state = _state.asStateFlow()


    fun onEvent(event: QrCodeEvent) {
        when (event) {
            is QrCodeEvent.OnQrCodeScanned -> handleQrCodeScanned(event.qrCode)


        }
    }

    private fun handleQrCodeScanned(qrCode: String) {
        //TODO... if some logic is needed + save to sharedPref
        setQrCode(qrCode)
        viewModelScope.launch {
            delay(3000)
            setNavigationRoute(MainNavigationRoutes.Login)
        }
    }

    private fun setQrCode(qrCode: String) {
        _state.update {
            it.copy(
                qrCode = qrCode
            )
        }
    }

    private fun setNavigationRoute(navigationRoute: NavigationRoutes) {
        _state.update {
            it.copy(
                navigationRoute = navigationRoute
            )
        }

    }
}