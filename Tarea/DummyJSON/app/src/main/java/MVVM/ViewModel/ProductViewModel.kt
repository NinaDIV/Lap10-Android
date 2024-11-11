package MVVM.ViewModel

import MVVM.API.ApiService
import MVVM.Model.ProductModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class ProductViewModel(private val apiService: ApiService) : ViewModel() {

    private val _productList = MutableLiveData<List<ProductModel>>()
    val productList: LiveData<List<ProductModel>> get() = _productList

    private val _productDetail = MutableLiveData<ProductModel?>()
    val productDetail: LiveData<ProductModel?> get() = _productDetail

    // Función para obtener la lista de productos
    fun fetchProducts() {
        viewModelScope.launch {
            try {
                // Cambia aquí para obtener ProductResponse
                val response = apiService.getProducts()
                _productList.value = response.products // Ahora obtenemos la lista de productos
            } catch (e: Exception) {
                // Manejar el error (por ejemplo, mostrar un mensaje)
                _productList.value = emptyList() // Mostrar una lista vacía si hay un error
            }
        }
    }

    // Función para obtener un producto por ID
    fun fetchProductById(id: Int) {
        viewModelScope.launch {
            try {
                val product = apiService.prodId(id)
                _productDetail.value = product // Guardamos el producto en LiveData
            } catch (e: Exception) {
                // Manejar el error
                _productDetail.value = null // Mostrar null si hay un error
            }
        }
    }
}
