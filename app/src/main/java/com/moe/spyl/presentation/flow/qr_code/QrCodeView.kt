package com.moe.spyl.presentation.flow.qr_code

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices.TABLET
import androidx.compose.ui.tooling.preview.Preview
import com.moe.spyl.ui.theme.SpylTheme

@Composable
fun QrCodeView() {

}


@Preview(name = "Tablet - Landscape", device = TABLET, showSystemUi = true)
@Composable
private fun QrCodeViewPreview() {
    SpylTheme {
        QrCodeView()
    }
}