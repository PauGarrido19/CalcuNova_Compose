package com.example.calcunova_compose.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val EsquemaClaro = lightColorScheme(
    background = Color(0xFFF5F7FA),
    surface = Color(0xFFFFFFFF),
    primary = Color(0xFF3F51B5),
    onPrimary = Color(0xFFF5F7FA),
    secondary = Color(0xFFFF9800),
    onSecondary = Color(0xFF000000),
    secondaryContainer = Color(0xFFE3E8F0),
    onSecondaryContainer = Color(0xFF212121),
    onBackground = Color(0xFF212121),
    onSurface = Color(0xFF212121)
)

private val EsquemaOscuro = darkColorScheme(
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    primary = Color(0xFF9FA8DA),
    onPrimary = Color(0xFF121212),
    secondary = Color(0xFFFFB74D),
    onSecondary = Color(0xFF000000),
    secondaryContainer = Color(0xFF2A2D34),
    onSecondaryContainer = Color(0xFFECEFF1),
    onBackground = Color(0xFFECEFF1),
    onSurface = Color(0xFFECEFF1)
)

@Composable
fun CalcuNova_ComposeTheme(darkTheme: Boolean, content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = if (darkTheme) EsquemaOscuro else EsquemaClaro,
        content = content
    )
}