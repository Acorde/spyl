package com.moe.spyl.login.presentation

import com.moe.spyl.login.presentation.models.LoginCompanyCodeEvent

fun reduceCompanyCode(current: String, event: LoginCompanyCodeEvent): String {
    fun norm(s: String) = s.padEnd(4, ' ').take(4)
    return when (event) {
        is LoginCompanyCodeEvent.DigitChanged -> {
            val buf = StringBuilder(norm(current))
            buf.setCharAt(event.index, event.text.firstOrNull() ?: ' ')
            buf.toString().trimEnd()
        }

        is LoginCompanyCodeEvent.Backspace -> {
            val buf = StringBuilder(norm(current))
            val i = (event.index - 1).coerceAtLeast(0)
            buf.setCharAt(i, ' ')
            buf.toString().trimEnd()
        }

        is LoginCompanyCodeEvent.Paste -> {
            val buf = StringBuilder(norm(current))
            var i = event.index
            for (d in event.digits.filter { it.isDigit() }.take(4)) {
                if (i > 3) break
                buf.setCharAt(i, d)
                i++
            }
            buf.toString().trimEnd()
        }
    }
}