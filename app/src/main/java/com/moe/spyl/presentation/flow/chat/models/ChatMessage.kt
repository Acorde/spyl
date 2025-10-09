package com.moe.spyl.presentation.flow.chat.models

import java.util.UUID

sealed class ChatMessage(val id: String = UUID.randomUUID().toString()) {
    data class CustomerMessage(val text: String) : ChatMessage()
    data class EmployeeMessage(val text: String) : ChatMessage()

}