package com.moe.spyl.presentation.flow.login.components

import android.util.Log
import android.view.KeyEvent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import com.moe.spyl.ui.theme.SpylTheme

@Composable
fun SingleOtpView(
    modifier: Modifier = Modifier,
    number: Int? = null,
    focusRequester: FocusRequester,
    onFocusChanged: (Boolean) -> Unit = {},
    onNumberChange: (Int?) -> Unit = {},
    onKeyboardBack: () -> Unit = {}
) {

    var text by remember(number) {
        mutableStateOf(
            TextFieldValue(
                text = number?.toString().orEmpty(),
                selection = TextRange(
                    index = if (number != null) 1 else 0
                )
            )
        )
    }

    var isFocused by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .border(width = 1.dp, color = Color.LightGray)
            .background(Color(0xFFEAEAEA)),
        contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            modifier = Modifier
                .padding(10.dp)
                .focusRequester(focusRequester)
                .onFocusChanged {
                    isFocused = it.isFocused
                    onFocusChanged(it.isFocused)
                }
                .onPreviewKeyEvent { event ->
                    if (event.type == KeyEventType.KeyDown && event.key == Key.Backspace) {
                        // If this cell is already empty, treat as "go to previous cell"
                        if (text.text.isEmpty()) {
                            onKeyboardBack()
                            return@onPreviewKeyEvent true // consumed
                        }
                    }
                    false
                },
            value = text,
            onValueChange = { newText ->
                val newNumber = newText.text
                if (newNumber.length <= 1 && newNumber.isDigitsOnly()) {
                    onNumberChange(newNumber.toIntOrNull())
                }
            },
            cursorBrush = SolidColor(Color.LightGray),
            singleLine = true,
            textStyle = TextStyle(
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Light,
                fontSize = 36.sp,
                color = Color.Black
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Next
            )

        )
    }

}

@Preview
@Composable
private fun SingleOtpViewPreview() {
    SpylTheme {
        var password by remember { mutableStateOf<Int?>(null) }
        val focusRequester = remember { FocusRequester() }

        SingleOtpView(
            modifier = Modifier.size(width = 60.dp, height = 60.dp),
            number = password,
            focusRequester = focusRequester,
            onNumberChange = {
                password = it
            }
        )
    }
}