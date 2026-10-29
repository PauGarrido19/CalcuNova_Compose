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
                    }
                }
            }
        }
    }
}
