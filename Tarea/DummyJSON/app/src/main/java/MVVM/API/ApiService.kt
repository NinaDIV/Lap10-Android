package MVVM.API

import MVVM.Model.ProductModel
import MVVM.Model.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("products")
    suspend fun getProducts(): ProductResponse

    @GET("products/{id}")
    suspend fun prodId(@Path("id") id: Int): ProductModel
}

