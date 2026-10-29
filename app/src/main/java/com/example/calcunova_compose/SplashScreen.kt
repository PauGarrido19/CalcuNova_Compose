package com.example.calcunova_compose

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Calculate
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calcunova_compose.ui.theme.CalcuNova_ComposeTheme
import androidx.compose.foundation.background

@Composable
fun SplashScreen(
    oscuro: Boolean = false,
    onModo: () -> Unit = {},
    onEntrar: () -> Unit = {}
) {
    val colores = MaterialTheme.colorScheme

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colores.background)
            .systemBarsPadding()
            .padding(24.dp)
    ) {
        IconButton(
            onClick = onModo,
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            Icon(
                imageVector = if (oscuro) Icons.Outlined.LightMode else Icons.Outlined.DarkMode,
                contentDescription = "Modo día/noche",
                tint = colores.primary
            )
        }

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Outlined.Calculate,
                contentDescription = null,
                modifier = Modifier.size(120.dp),
                tint = colores.primary
            )
            Spacer(Modifier.height(24.dp))
            Text(
                "CalcuNova",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = colores.primary
            )
            Text("Calculadora básica", fontSize = 18.sp, color = colores.onBackground)
            Text("2DAM · 2026-2027", fontSize = 14.sp, color = colores.onBackground)
        }

        Button(
            onClick = onEntrar,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(64.dp)
        ) {
            Text("Entrar", fontSize = 20.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreviewClaro() {
    CalcuNova_ComposeTheme(darkTheme = false) { SplashScreen() }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreviewOscuro() {
    CalcuNova_ComposeTheme(darkTheme = true) { SplashScreen(oscuro = true) }
}