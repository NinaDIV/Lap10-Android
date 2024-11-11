package MVVM.ViewModel


import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import MVVM.MODEL.Amphibian
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import MVVM.Repository.AmphibianRepository

class AmphibianViewModel(private val repository: AmphibianRepository) : ViewModel() {
    private val _amphibians = MutableLiveData<List<Amphibian>>()
    val amphibians: LiveData<List<Amphibian>> get() = _amphibians

    init {
        fetchAmphibians()
    }

    private fun fetchAmphibians() {
        viewModelScope.launch {
            try {
                _amphibians.value = repository.getAmphibians()
            } catch (e: Exception) {
                _amphibians.value = emptyList() // Maneja errores aquí si es necesario
            }
        }
    }
}