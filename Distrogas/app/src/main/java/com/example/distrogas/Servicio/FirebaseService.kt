package com.example.distrogas.Servicio

// FirebaseService.kt
import com.example.distrogas.Modelo.ProductModel
import com.example.distrogas.Modelo.SaleModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirebaseService {
    private val db: FirebaseFirestore = Firebase.firestore

    // CRUD para Productos
    fun addProduct(product: ProductModel , callback: (Boolean) -> Unit) {
        db.collection("products").add(product)
            .addOnSuccessListener { callback(true) }
            .addOnFailureListener { callback(false) }
    }

    fun getProducts(callback: (List<ProductModel>) -> Unit) {
        db.collection("products").get().addOnSuccessListener { documents ->
            val products = documents.map { it.toObject(ProductModel::class.java) }
            callback(products)
        }
    }

    fun updateProduct(productId: String, updatedProduct: ProductModel, callback: (Boolean) -> Unit) {
        db.collection("products").document(productId).set(updatedProduct)
            .addOnSuccessListener { callback(true) }
            .addOnFailureListener { callback(false) }
    }

    fun deleteProduct(productId: String, callback: (Boolean) -> Unit) {
        db.collection("products").document(productId).delete()
            .addOnSuccessListener { callback(true) }
            .addOnFailureListener { callback(false) }
    }

    // CRUD para Ventas
    fun addSale(sale: SaleModel, callback: (Boolean) -> Unit) {
        db.collection("sales").add(sale)
            .addOnSuccessListener { callback(true) }
            .addOnFailureListener { callback(false) }
    }

    fun getSales(callback: (List<SaleModel>) -> Unit) {
        db.collection("sales").get().addOnSuccessListener { documents ->
            val sales = documents.map { it.toObject(SaleModel::class.java) }
            callback(sales)
        }
    }

    fun updateSale(saleId: String, updatedSale: SaleModel, callback: (Boolean) -> Unit) {
        db.collection("sales").document(saleId).set(updatedSale)
            .addOnSuccessListener { callback(true) }
            .addOnFailureListener { callback(false) }
    }

    fun deleteSale(saleId: String, callback: (Boolean) -> Unit) {
        db.collection("sales").document(saleId).delete()
            .addOnSuccessListener { callback(true) }
            .addOnFailureListener { callback(false) }
    }
}
