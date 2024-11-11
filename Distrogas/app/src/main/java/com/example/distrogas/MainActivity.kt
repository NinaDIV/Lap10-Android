package com.example.distrogas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.distrogas.Servicio.FirebaseService
import com.example.distrogas.Vistas.ProductScreen
import com.example.distrogas.Vistas.SalesScreen
import com.example.distrogas.ui.theme.DistrogasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DistrogasTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colorScheme.background) {
                    // Inicializa el servicio de Firebase
                    val firebaseService = remember { FirebaseService() }

                    // Navegación entre pantallas (puedes usar Navigation Compose para una mejor navegación)
                    MainScreen(firebaseService)
                }
            }
        }
    }
}

@Composable
fun MainScreen(firebaseService: FirebaseService) {
    // Aquí puedes usar un `BottomNavigation` o `TabLayout` para cambiar entre pantallas
    // Por simplicidad, solo vamos a mostrar la pantalla de productos y ventas
    Column {
        Text("Gestión de Ventas de Gas", style = MaterialTheme.typography.titleLarge)

        // Pantalla de Productos
        ProductScreen(firebaseService)

        // Espacio para separar las pantallas
        Spacer(modifier = Modifier.height(16.dp))

        // Pantalla de Ventas
        SalesScreen(firebaseService)
    }
}