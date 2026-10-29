package com.example.calcunova_compose

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.calcunova_compose.ui.theme.CalcuNova_ComposeTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("ContextCastToActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val sistemaOscuro = isSystemInDarkTheme()
            var oscuro by rememberSaveable { mutableStateOf(sistemaOscuro) }
            val nav = rememberNavController()
            val activity = LocalContext.current as Activity

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
                                onSalir = {
                                    nav.navigate("despedida") {
                                        popUpTo("calculadora") { inclusive = true }
                                    }
                                }
                            )
                        }

                        composable("despedida") {
                            FarewellScreen(
                                oscuro = oscuro,
                                onModo = { oscuro = !oscuro },
                                onVolver = {
                                    nav.navigate("splash") {
                                        popUpTo("splash") { inclusive = true }
                                    }
                                },
                                onCerrar = { activity.finishAffinity() }
                            )
                        }
                    }
                }
            }
        }
    }
}