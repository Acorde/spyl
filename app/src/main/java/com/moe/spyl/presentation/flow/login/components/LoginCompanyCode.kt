package com.moe.spyl.presentation.flow.login.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.moe.spyl.presentation.flow.login.models.LoginCompanyCodeEvent
import com.moe.spyl.presentation.flow.login.models.LoginState
import com.moe.spyl.presentation.flow.login.reduceCompanyCode
import com.moe.spyl.presentation.flow.login.preview.LoginStatePreviewParameterProvider
import com.moe.spyl.ui.theme.SpylTheme

@Composable
fun LoginCompanyCode(
    code: String,
    onEvent: (LoginCompanyCodeEvent) -> Unit = {},
    onTestEvent: (String) -> Unit = {}
) {

    // Focus helpers for OTP fields
    val focusers = remember { List(4) { FocusRequester() } }
    val padded = remember(code) { code.padEnd(4, ' ').take(4) }


    LaunchedEffect(code) {
        val next = code.length.coerceIn(0, 3) // after last typed digit
        focusers[next].requestFocus()
    }

    Column {
        Text(
            modifier = Modifier.align(Alignment.Start),
            text = "Company Code",
            style = MaterialTheme.typography.labelMedium.copy(color = Color(0xFF6B7280))
        )
        Spacer(Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            repeat(4) { index ->
                val cell = padded[index].takeIf { it != ' ' }?.toString() ?: ""
                SingleDigitBox(
                    modifier = Modifier
                        .size(width = 62.dp, height = 54.dp)
                        .focusRequester(focusers[index]),
                    value = cell,
                    onValueChange = { input ->
                        onTestEvent(input)
                        val digits = input.filter { it.isDigit() }
                        when {
                            digits.isEmpty() -> onEvent(
                                LoginCompanyCodeEvent.DigitChanged(
                                    index,
                                    ""
                                )
                            )

                            digits.length == 1 -> onEvent(
                                LoginCompanyCodeEvent.DigitChanged(
                                    index,
                                    digits
                                )
                            )

                            else -> onEvent(LoginCompanyCodeEvent.Paste(index, digits))
                        }
                    },
                    onBackspaceOnEmpty = { onEvent(LoginCompanyCodeEvent.Backspace(index)) },

                    )
            }
        }
    }
}

@Composable
private fun SingleDigitBox(

    value: String,
    onValueChange: (String) -> Unit,
    onBackspaceOnEmpty: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        modifier = modifier
            .onKeyEvent { keyEvent ->
                if (value.isEmpty() && keyEvent.key == Key.Backspace) {
                    onBackspaceOnEmpty(); true
                } else false
            },
        value = value,
        onValueChange = onValueChange,
        textStyle = TextStyle(textAlign = TextAlign.Center, fontSize = 18.sp),
        singleLine = true,
        colors = TextFieldDefaults.colors(
            // text / cursor
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            cursorColor = Color.Transparent,

            // container
            focusedContainerColor = Color(0xFFEAEAEA),
            unfocusedContainerColor = Color(0xFFEAEAEA),


            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent
        ),
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword,
            imeAction = ImeAction.Next
        ),
        placeholder = { Text("") }
    )
}

@Preview(showSystemUi = true)
@Composable
private fun LoginCompanyCodePreview(
    @PreviewParameter(LoginStatePreviewParameterProvider::class)
    state: LoginState
) {
    var code by remember { mutableStateOf(state.companyCode) }
    SpylTheme {
        LoginCompanyCode(
            code = code,
            onEvent = { e -> code = reduceCompanyCode(code, e) })
    }
}