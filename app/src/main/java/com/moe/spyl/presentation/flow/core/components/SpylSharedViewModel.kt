package com.moe.spyl.presentation.flow.core.components

import androidx.lifecycle.ViewModel
import com.moe.spyl.presentation.flow.core.models.SharedViewmodelState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class SpylSharedViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(SharedViewmodelState())
    val state = _state.asStateFlow()


    private fun onEvent(event: SharedEvents) {

    }
}