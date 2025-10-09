package com.moe.spyl.presentation.flow.chat.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.moe.spyl.ui.theme.SpylTheme


@Composable
fun ChatPulseAnimatedButton(
    modifier: Modifier = Modifier,
    size: Dp = 72.dp,            // diameter of the inner "button" circle stack
    waves: Int = 3,
    waveMaxExtra: Dp = 48.dp,    // how far waves extend beyond button radius
    durationMs: Int = 2200,
    // Circle colors (match your example)
    outerCircle: Color = Color(0xFFE6EBF1),  // very light
    middleCircle: Color = Color(0xFFBCC6D2), // medium
    innerCircle: Color = Color(0xFF5F6A75),  // dark
    rectangleColor: Color = Color.White,
    rectangleSize: Dp = 22.dp,
    rectangleCorner: Dp = 4.dp,
    waveColor: Color = Color(0xFF5F6A75), // use inner tone for waves
    ringStyle: Boolean = false,   // true = ring waves; false = filled pulses
    onClick: () -> Unit = {}
) {
    val density = LocalDensity.current
    val buttonRadiusPx = with(density) { (size / 2).toPx() }
    val maxRadiusPx = with(density) { (size / 2 + waveMaxExtra).toPx() }
    val rectSizePx = with(density) { rectangleSize.toPx() }
    val rectCornerPx = with(density) { rectangleCorner.toPx() }

    // Base 0..1 looping value; other waves are phase-shifted
    val transition = rememberInfiniteTransition(label = "waves")
    val base = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMs, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "base"
    ).value

    Box(
        modifier = modifier
            .size(size + waveMaxExtra * 2)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        // Animated waves (behind)
        Canvas(Modifier.matchParentSize()) {
            val c = center
            repeat(waves) { i ->
                val phase = ((base + i.toFloat() / waves) % 1f)
                val r = buttonRadiusPx + (maxRadiusPx - buttonRadiusPx) * phase
                val alpha = (1f - phase).coerceIn(0f, 1f) * 0.35f

                if (ringStyle) {
                    drawCircle(
                        color = waveColor.copy(alpha = alpha),
                        radius = r,
                        center = c,
                        style = Stroke(width = (maxRadiusPx - buttonRadiusPx) * 0.12f)
                    )
                } else {
                    drawCircle(
                        color = waveColor.copy(alpha = alpha),
                        radius = r,
                        center = c
                    )
                }
            }
        }

        // Concentric circles + center rectangle (foreground)
        Canvas(Modifier.size(size)) {
            val c = Offset(this.size.width / 2f, this.size.height / 2f)

            // Tunable ratios (outer = 1f). Bump inner to make the ring around the square thicker.
            val outerRatio = 1.00f
            val middleRatio = 0.9f
            val innerRatio = 0.8f   // was 0.50f — increase this

            val rOuter = this.size.minDimension / 2f * outerRatio
            val rMiddle = this.size.minDimension / 2f * middleRatio
            val rInner = this.size.minDimension / 2f * innerRatio

            drawCircle(color = outerCircle, radius = rOuter, center = c)
            drawCircle(color = middleCircle, radius = rMiddle, center = c)
            drawCircle(color = innerCircle, radius = rInner, center = c)

            // (Optional) slightly smaller square so the inner ring reads thicker
            val rectSizePx = with(density) { (rectangleSize * 0.9f).toPx() }

            drawRoundRect(
                color = rectangleColor,
                topLeft = Offset(c.x - rectSizePx / 2f, c.y - rectSizePx / 2f),
                size = Size(rectSizePx, rectSizePx),
                cornerRadius = CornerRadius(rectCornerPx, rectCornerPx)
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
private fun ChatPulseAnimatedButtonPreview(

) {
    SpylTheme {
        ChatPulseAnimatedButton(
            size = 50.dp,
            waves = 2,
            waveMaxExtra = 40.dp,
            ringStyle = false // set false for filled pulses
        )
    }
}