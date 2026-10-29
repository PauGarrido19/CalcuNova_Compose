package com.example.calcunova_compose


import androidx.compose.foundation.background
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

@Composable
fun FarewellScreen(
    oscuro: Boolean = false,
    onModo: () -> Unit = {},
    onVolver: () -> Unit = {},
    onCerrar: () -> Unit = {}
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
                "¡Hasta pronto!",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = colores.primary
            )
            Spacer(Modifier.height(8.dp))
            Text(
                "Gracias por usar CalcuNova",
                fontSize = 18.sp,
                color = colores.onBackground
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        ) {
            Button(
                onClick = onVolver,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
            ) {
                Text("Volver a empezar", fontSize = 20.sp)
            }
            Spacer(Modifier.height(12.dp))
            OutlinedButton(
                onClick = onCerrar,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
            ) {
                Text("Cerrar app", fontSize = 20.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FarewellScreenPreviewClaro() {
    CalcuNova_ComposeTheme(darkTheme = false) { FarewellScreen() }
}

@Preview(showBackground = true)
@Composable
fun FarewellScreenPreviewOscuro() {
    CalcuNova_ComposeTheme(darkTheme = true) { FarewellScreen(oscuro = true) }
}