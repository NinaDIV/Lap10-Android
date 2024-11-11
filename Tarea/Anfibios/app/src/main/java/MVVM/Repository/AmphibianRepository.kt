package MVVM.Repository



import MVVM.API.ApiService
import MVVM.MODEL.Amphibian

class AmphibianRepository(private val apiService: ApiService) {
    suspend fun getAmphibians(): List<Amphibian> {
        return apiService.getAmphibians()
    }
}
