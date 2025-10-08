package com.moe.spyl.data.core.local

import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf

@Stable
data class AppWindowSize(
    val width: WindowWidthSizeClass,
    val height: WindowHeightSizeClass
)

val LocalAppWindowSize = staticCompositionLocalOf {
    AppWindowSize(WindowWidthSizeClass.Compact, WindowHeightSizeClass.Compact)
}