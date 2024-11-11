package com.example.distrogas.Vistas

import androidx.compose.runtime.Composable
import com.example.distrogas.Servicio.FirebaseService
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.distrogas.Modelo.ProductModel
import com.example.distrogas.Modelo.SaleModel


@Composable
fun SalesScreen(firebaseService: FirebaseService) {
    val sales = remember { mutableStateListOf<SaleModel>() }
    val productId = remember { mutableStateOf("") }
    val quantity = remember { mutableStateOf("") }
    val price = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        firebaseService.getSales { saleList ->
            sales.clear()
            sales.addAll(saleList)
        }
    }

    Column {
        // Formulario para registrar venta
        TextField(value = productId.value, onValueChange = { productId.value = it }, label = { Text("ID del Producto") })
        TextField(value = quantity.value, onValueChange = { quantity.value = it }, label = { Text("Cantidad") })
        TextField(value = price.value, onValueChange = { price.value = it }, label = { Text("Precio") })

        Button(onClick = {
            val newSale = SaleModel(productId = productId.value, quantity = quantity.value.toInt(), price = price.value.toDouble())
            firebaseService.addSale(newSale) { success ->
                if (success) {
                    productId.value = ""
                    quantity.value = ""
                    price.value = ""
                }
            }
        }) {
            Text("Registrar Venta")
        }

        LazyColumn {
            items(sales) { sale ->
                Text(text = "Venta: ${sale.quantity} - Precio: ${sale.price}")
                // Botones para actualizar y eliminar
                Button(onClick = {
                    // Lógica para actualizar
                }) {
                    Text("Actualizar")
                }
                Button(onClick = {
                    firebaseService.deleteSale(sale.id) { success ->
                        if (success) {
                            sales.remove(sale)
                        }
                    }
                }) {
                    Text("Eliminar")
                }
            }
        }
    }
}
