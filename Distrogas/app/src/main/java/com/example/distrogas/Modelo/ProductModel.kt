package com.example.distrogas.Modelo

// ProductModel.kt
data class ProductModel(
    var id: String = "",
    var type: String = "", // Normal o Premium
    var brand: String = "",
    var price: Double = 0.0,
    var category: String = "" // Gas o Accesorios
)

// SaleModel.kt
data class SaleModel(
    var id: String = "",
    var productId: String = "",
    var quantity: Int = 0,
    var price: Double = 0.0,
    var date: String = "" // Fecha de la venta
)
