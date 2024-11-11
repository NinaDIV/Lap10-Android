package MVVM.API


import MVVM.MODEL.Amphibian
import retrofit2.http.GET

interface ApiService {
    @GET("amphibians")
    suspend fun getAmphibians(): List<Amphibian>
}

