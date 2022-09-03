package com.example.todo.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

val TwilightColorPalette = darkColorScheme(
    primary = TwilightPrimary,
    primaryContainer = TwilightPrimary,
    onPrimary = TwilightOn,
    onPrimaryContainer = TwilightOn,
    secondary = TwilightSecondary,
    secondaryContainer = TwilightSecondaryVariant,
    onSecondary = TwilightOn,
    onSecondaryContainer = TwilightOn,
    background = TwilightBackground,
    onBackground = TwilightOn,
    surface = TwilightSecondaryVariant,
    onSurface = TwilightOn,
    error = Error,
    onError = Color.White,
)

val LightColorPalette = lightColorScheme(
    primary = LightPrimary,
    primaryContainer = LightPrimary,
    onPrimary = Color.White,
    onPrimaryContainer = Color.White,
    secondary = LightSecondary,
    secondaryContainer = LightSecondaryVariant,
    onSecondary = Color.Black,
    onSecondaryContainer = Color.Black,
    background = LightBackground,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black,
    error = LightError,
    onError = Color.White,
)

@Composable
fun ToDoTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        TwilightColorPalette
    } else {
        LightColorPalette
    }

    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = colors.surface,
            darkIcons = !darkTheme,
            isNavigationBarContrastEnforced = false,
        )
    }
    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}