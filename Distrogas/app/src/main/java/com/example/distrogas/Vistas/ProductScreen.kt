package com.example.distrogas.Vistas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.distrogas.Modelo.ProductModel
import com.example.distrogas.Servicio.FirebaseService

@Composable
fun ProductScreen(firebaseService: FirebaseService) {
    val products = remember { mutableStateListOf<ProductModel>() }
    val productType = remember { mutableStateOf("") }
    val productBrand = remember { mutableStateOf("") }
    val productPrice = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        firebaseService.getProducts { productList ->
            products.clear()
            products.addAll(productList)
        }
    }

    Column {
        // Formulario para agregar producto
        TextField(value = productType.value, onValueChange = { productType.value = it }, label = { Text("Tipo de Gas") })
        TextField(value = productBrand.value, onValueChange = { productBrand.value = it }, label = { Text("Marca") })
        TextField(value = productPrice.value, onValueChange = { productPrice.value = it }, label = { Text("Precio") })

        Button(onClick = {
            val newProduct = ProductModel(type = productType.value, brand = productBrand.value, price = productPrice.value.toDouble())
            firebaseService.addProduct(newProduct) { success ->
                if (success) {
                    productType.value = ""
                    productBrand.value = ""
                    productPrice.value = ""
                }
            }
        }) {
            Text("Agregar Producto")
        }

        LazyColumn {
            items(products) { product ->
                Row {
                    Text(text = "${product.type} - ${product.brand} - ${product.price}")
                    // Botones para actualizar y eliminar
                    Button(onClick = {
                        // Lógica para actualizar
                    }) {
                        Text("Actualizar")
                    }
                    Button(onClick = {
                        firebaseService.deleteProduct(product.id) { success ->
                            if (success) {
                                products.remove(product)
                            }
                        }
                    }) {
                        Text("Eliminar")
                    }
                }
            }
        }
    }
}
