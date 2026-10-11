package com.example.jcbmi.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkAppleColorScheme = darkColorScheme(
    primary = Color.White,
    secondary = Color.White.copy(alpha = 0.7f),
    tertiary = Color.White.copy(alpha = 0.5f),
    background = AppleBackgroundDark,
    surface = AppleGlassSurfaceDark,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = AppleTextPrimaryDark,
    onSurface = AppleTextPrimaryDark
)

private val LightAppleColorScheme = lightColorScheme(
    primary = Color.Black,
    secondary = Color.Black.copy(alpha = 0.7f),
    tertiary = Color.Black.copy(alpha = 0.5f),
    background = AppleBackgroundLight,
    surface = AppleGlassSurfaceLight,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onTertiary = Color.Black,
    onBackground = AppleTextPrimaryLight,
    onSurface = AppleTextPrimaryLight
)

@Composable
fun JcbmiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkAppleColorScheme else LightAppleColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
