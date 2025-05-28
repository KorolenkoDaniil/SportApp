package com.example.sportapp.ui.theme

import android.annotation.SuppressLint
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.sportapp.domain.viewModels.authorization.AuthViewModel

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

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun AppTheme(
    authViewModel: AuthViewModel,
    content: @Composable () -> Unit
) {

    val themeISWhite = authViewModel.themeIsWhite.collectAsState().value

    val colors = if (themeISWhite) {
        LightColors
    } else {
        DarkColors
    }

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}