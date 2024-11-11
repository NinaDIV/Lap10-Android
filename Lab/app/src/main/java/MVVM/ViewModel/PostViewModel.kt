package MVVM.ViewModel

import MVVM.API.PostApiService
import MVVM.Model.PostModel
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PostViewModel(private val servicio: PostApiService) : ViewModel() {
    var listaPosts = mutableStateListOf<PostModel>()
    var postDetail = mutableStateOf<PostModel?>(null)

    fun obtenerPosts() {
        viewModelScope.launch {
            listaPosts.clear()
            listaPosts.addAll(servicio.getUserPosts())
        }
    }

    fun obtenerPostPorId(id: Int) {
        viewModelScope.launch {
            postDetail.value = servicio.getUserPostById(id)
        }
    }

    fun crearPost(post: PostModel) {
        viewModelScope.launch {
            val nuevoPost = servicio.createPost(post)
            listaPosts.add(nuevoPost)
        }
    }

    fun actualizarPost(id: Int, post: PostModel) {
        viewModelScope.launch {
            val postActualizado = servicio.updatePost(id, post)
            val index = listaPosts.indexOfFirst { it.id == id }
            if (index != -1) {
                listaPosts[index] = postActualizado
            }
        }
    }

    fun eliminarPost(id: Int) {
        viewModelScope.launch {
            servicio.deletePost(id)
            listaPosts.removeAll { it.id == id }
        }
    }
}
