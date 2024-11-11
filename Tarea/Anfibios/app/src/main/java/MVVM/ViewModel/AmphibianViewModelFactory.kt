package MVVM.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import MVVM.Repository.AmphibianRepository

class AmphibianViewModelFactory(private val repository: AmphibianRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AmphibianViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AmphibianViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
