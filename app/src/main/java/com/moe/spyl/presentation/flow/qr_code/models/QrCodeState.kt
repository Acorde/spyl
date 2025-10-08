package com.moe.spyl.presentation.flow.qr_code.models

import com.moe.spyl.presentation.flow.core.navigation.NavigationRoutes

data class QrCodeState(
    val qrCode: String = "",
    val navigationRoute: NavigationRoutes? = null
)
