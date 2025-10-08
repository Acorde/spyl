package com.moe.spyl.presentation.flow.login

sealed interface QrCodeEvent {
    data class OnQrCodeScanned(val qrCode: String) : QrCodeEvent
}