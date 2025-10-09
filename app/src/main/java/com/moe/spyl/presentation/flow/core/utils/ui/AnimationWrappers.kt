package com.moe.spyl.presentation.flow.core.utils.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalInspectionMode
import kotlinx.coroutines.delay


@Composable
fun rememberInitialDelayVisibility(
    delay: Long,
    key: String? = null,
): Boolean {
    var visibility by rememberSaveable(key = key) { mutableStateOf(false) }
    LaunchedEffect(
        key1 = key ?: Unit,
        block = {
            delay(delay)
            visibility = true
        }
    )
    return visibility
}

@Composable
fun InitialAnimationWrapper(
    modifier: Modifier = Modifier,
    key: String? = null,
    content: @Composable () -> Unit
) {
    val visible = rememberInitialDelayVisibility(delay = 10, key = key)
    val isPreviewMode = LocalInspectionMode.current

    val animatedSAlpha by animateFloatAsState(
        targetValue = if (visible || isPreviewMode) 1f else 0f,
        label = "animatedSlideVertically",
        animationSpec = spring(dampingRatio = 0.4f, stiffness = 100f)
    )

    val animatedSlideVertically by animateFloatAsState(
        targetValue = if (visible || isPreviewMode) 0f else 20f,
        label = "animatedSlideVertically",
        animationSpec = spring(dampingRatio = 0.4f, stiffness = 100f)
    )

    Box(
        modifier = modifier.graphicsLayer {
            alpha = animatedSAlpha
            translationY = animatedSlideVertically
        }
    ) {
        content()
    }
}