package com.example.calcunova_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.calcunova_compose.ui.theme.CalcuNova_ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val sistemaOscuro = isSystemInDarkTheme()
            var oscuro by rememberSaveable { mutableStateOf(sistemaOscuro) }
            val nav = rememberNavController()

            CalcuNova_ComposeTheme(darkTheme = oscuro) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = nav, startDestination = "splash") {
                        composable("splash") {
                            SplashScreen(
                                oscuro = oscuro,
                                onModo = { oscuro = !oscuro },
                                onEntrar = { nav.navigate("calculadora") }
                            )
                        }
                        composable("calculadora") {
                            CalculatorScreen(
                                oscuro = oscuro,
                                onModo = { oscuro = !oscuro },
                                onSalir = { nav.navigate("despedida") }
                            )
                        }
                        composable("despedida") {
                            Text("Despedida") // la hacemos en el siguiente paso
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SplashScreen(oscuro: Boolean, onModo: () -> Unit, onEntrar: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(24.dp)
    ) {
        Box(modifier = Modifier.align(Alignment.TopEnd)) {
            TextButton(onClick = onModo) {
                Text(if (oscuro) "☀️" else "🌙", fontSize = 28.sp)
            }
        }

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("➕➖✖️➗", fontSize = 48.sp)
            Spacer(Modifier.height(24.dp))
            Text(
                "CalcuNova",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Text("Calculadora básica", fontSize = 18.sp)
            Spacer(Modifier.height(32.dp))
            Text("Creado por", fontSize = 14.sp)
            Text("Nombre 1 · Nombre 2", fontSize = 16.sp)
            Text("Curso · 2026", fontSize = 14.sp)
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