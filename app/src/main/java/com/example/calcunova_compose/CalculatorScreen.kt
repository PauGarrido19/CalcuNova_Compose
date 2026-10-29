package com.example.calcunova_compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calcunova_compose.ui.theme.CalcuNova_ComposeTheme

@Composable
fun CalculatorScreen(
    oscuro: Boolean = false,
    onModo: () -> Unit = {},
    onSalir: () -> Unit = {},
) {
    val colores = MaterialTheme.colorScheme
    var mostrarInfo by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colores.background)
            .systemBarsPadding()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = onSalir,
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colores.primary,
                    contentColor = colores.onPrimary
                ),
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
            ) {
                Text("Salir", fontSize = 18.sp)
            }
            Spacer(Modifier.width(8.dp))
            IconButton(onClick = { mostrarInfo = true }, modifier = Modifier.size(56.dp)) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Información",
                    tint = colores.primary
                )
            }
            IconButton(onClick = onModo, modifier = Modifier.size(56.dp)) {
                Icon(
                    imageVector = if (oscuro) Icons.Outlined.LightMode else Icons.Outlined.DarkMode,
                    contentDescription = "Modo día/noche",
                    tint = colores.primary
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
                .background(colores.surface)
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "12+7",
                fontSize = 24.sp,
                maxLines = 1,
                color = colores.onSurface,
            )
            Text(
                text = "19",
                fontSize = 56.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                color = colores.onSurface
            )
        }

        Spacer(Modifier.height(12.dp))

        val teclas = listOf(
            listOf("%", "CE", "C", "⌫"),
            listOf("1/x", "x²", "√x", "÷"),
            listOf("7", "8", "9", "x"),
            listOf("4", "5", "6", "-"),
            listOf("1", "2", "3", "+"),
            listOf("±", "0", ",", "=")
        )
        val operaciones = listOf("⌫", "÷", "x", "-", "+")

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(5f)
        ) {
            teclas.forEach { fila ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    fila.forEach { simbolo ->
                        val (fondo, texto) = when {
                            simbolo == "=" -> colores.primary to colores.onPrimary
                            simbolo in operaciones -> colores.secondary to Color.Black
                            else -> colores.secondaryContainer to colores.onSecondaryContainer
                        }
                        Button(
                            onClick = {},
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = fondo,
                                contentColor = texto
                            ),
                            contentPadding = PaddingValues(0.dp),
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .padding(4.dp)
                        ) {
                            Text(simbolo, fontSize = 24.sp)
                        }
                    }
                }
            }
        }
    }


    if (mostrarInfo) {
        AlertDialog(
            onDismissRequest = { mostrarInfo = false },
            title = { Text("CalcuNova") },
            text = {
                Text(
                    "Calculadora básica.\n\n" +
                            "• Números y operaciones: + − × ÷\n" +
                            "• C borra todo y CE borra la entrada actual\n" +
                            "• ⌫ borra el último dígito\n" +
                            "• ± cambia el signo, 1/x, x² y √x son funciones rápidas\n\n" +
                            "Creado por Nombre 1 · Nombre 2"
                )
            },
            confirmButton = {
                TextButton(onClick = { mostrarInfo = false }) {
                    Text("Aceptar")
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorScreenPreviewClaro() {
    CalcuNova_ComposeTheme(darkTheme = false) { CalculatorScreen() }
}

@Preview(showBackground = true)
@Composable
fun CalculatorScreenPreviewOscuro() {
    CalcuNova_ComposeTheme(darkTheme = true) { CalculatorScreen(oscuro = true) }
}