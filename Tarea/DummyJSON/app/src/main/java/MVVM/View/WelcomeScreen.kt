package MVVM.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun WelcomeScreen(navController: NavController) {
    // Fondo con un degradado para darle un toque visual
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFE3F2FD), // Color claro de fondo (Azul claro)
                        Color(0xFFFCFCFC)  // Otro tono claro (Azul)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            // Ícono decorativo en la parte superior
            Icon(
                imageVector = Icons.Outlined.ShoppingCart,
                contentDescription = "Carrito de compras",
                tint = Color(0xFF000000) , // Color azul oscuro para el ícono
                modifier = Modifier.size(64.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Título de bienvenida
            Text(
                text = "¡Bienvenido a la App de Productos!",
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = Color(0xFF000000) , // Título en azul oscuro
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Descripción de la app
            Text(
                text = "Explora una lista de productos usando nuestra API. ¡Descubre artículos interesantes y navega fácilmente!",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color(0xFF000000) , // Texto en azul oscuro
                    fontSize = 18.sp
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Botón para ir a la lista de productos
            Button(
                onClick = { navController.navigate("products") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF1ECF6) , // Color claro para el botón
                    contentColor = Color(0xFF0E0E0E) // Texto del botón en azul oscuro
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)

            ) {
                Text(
                    text = "Ver Productos",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
