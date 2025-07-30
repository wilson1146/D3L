package com.example.d2l.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    background = White,
    surface = TextColor,
    primary = BluePrimary,
    onPrimary = TextColor,
    onBackground = TextColor,
    onSurface = TextColor
)

@Composable
fun D2LTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = MyTypography,
        content = content
    )
}