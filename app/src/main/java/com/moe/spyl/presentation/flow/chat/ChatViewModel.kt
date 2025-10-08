package com.moe.spyl.presentation.flow.chat

import androidx.lifecycle.ViewModel
import com.moe.spyl.presentation.flow.chat.models.ChatEvent
import com.moe.spyl.presentation.flow.chat.models.ChatState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(ChatState())
    val state = _state.asStateFlow()


    fun onEvent(event: ChatEvent) {
        when (event) {
            else -> {}
        }
    }
}