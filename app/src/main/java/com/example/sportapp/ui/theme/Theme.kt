package com.example.sportapp.ui.theme

import AppActivityViewModel
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

private val LightColors = lightColorScheme(
    primary   = LightPrimary,
    secondary = LightSecondary,
    background= LightBackground,
    surface   = LightSurface,
    error     = LightError,
)

private val DarkColors = darkColorScheme(
    primary   = DarkPrimary,
    secondary = DarkSecondary,
    background= DarkBackground,
    surface   = DarkSurface,
    error     = DarkError,
)

@Composable
fun AppTheme(
    appActivity: AppActivityViewModel,
    content: @Composable () -> Unit
) {
    val isWhiteTheme by appActivity.appTheme.collectAsState()

    val colors = if (isWhiteTheme) {
        LightColors
    } else {
        DarkColors
    }

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}