package com.example.dummyjson

import MVVM.API.ApiService
import MVVM.View.ProductDetailScreen
import MVVM.View.ProductScreen
import MVVM.View.WelcomeScreen // Asegúrate de importar WelcomeScreen
import MVVM.ViewModel.ProductViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configura tu ApiService aquí utilizando Retrofit
        val apiService = createApiService()
        val productViewModel = ProductViewModel(apiService)

        setContent {
            MyApp {
                // Crea un controlador de navegación
                val navController = rememberNavController()

                // Mantén la estructura del Scaffold mientras navegas
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                Text(
                                    text = "Productos",
                                    style = MaterialTheme.typography.titleLarge,
                                    color = Color.Black // Color del texto
                                )
                            },
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.background, // Fondo del AppBar
                                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer, // Color del título
                                actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer // Color de los iconos de acción
                            ),

                        )
                    },
                    bottomBar = {
                        BottomNavigationBar(navController)
                    }
                ) { innerPadding ->
                    // El NavHost se mantiene dentro del Scaffold
                    NavHost(
                        navController = navController,
                        startDestination = "welcome", // Cambia el destino inicial a welcome
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("welcome") {
                            WelcomeScreen(navController) // Muestra la WelcomeScreen
                        }
                        composable("products") {
                            ProductScreen(productViewModel, navController)
                        }
                        composable("productDetail/{productId}") { backStackEntry ->
                            val productId = backStackEntry.arguments?.getString("productId")?.toIntOrNull()
                            if (productId != null) {
                                ProductDetailScreen(productViewModel, navController, productId)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun createApiService(): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiService::class.java)
    }
}

@Composable
fun BottomNavigationBar(navController: androidx.navigation.NavController) {
    // Barra de navegación inferior
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Outlined.Home, contentDescription = "Inicio") },
            label = { Text("Inicio") },
            selected = false,
            onClick = {
                navController.navigate("welcome") // Navega a la pantalla de bienvenida
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Outlined.ShoppingCart, contentDescription = "Productos") },
            label = { Text("Productos") },
            selected = false,
            onClick = {
                navController.navigate("products") // Navega a la pantalla de productos
            }
        )
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    MaterialTheme {
        Surface {
            content()
        }
    }
}
