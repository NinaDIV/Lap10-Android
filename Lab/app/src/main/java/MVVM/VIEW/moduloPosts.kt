package MVVM.View


import MVVM.Model.PostModel
import MVVM.ViewModel.PostViewModel
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun ScreenPosts(navController: NavHostController, postViewModel: PostViewModel) {
    LaunchedEffect(Unit) {
        postViewModel.obtenerPosts()
    }

    Box(modifier = Modifier.fillMaxSize()) { // Usar un Box para permitir la alineación
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(postViewModel.listaPosts) { item ->
                Row(modifier = Modifier.padding(8.dp)) {
                    Text(text = item.id.toString(), Modifier.weight(0.05f), textAlign = TextAlign.End)
                    Spacer(Modifier.padding(horizontal = 1.dp))
                    Text(text = item.title, Modifier.weight(0.7f))
                    IconButton(
                        onClick = {
                            navController.navigate("postsVer/${item.id}")
                            Log.e("POSTS", "ID = ${item.id}")
                        },
                        Modifier.weight(0.1f)
                    ) {
                        Icon(imageVector = Icons.Outlined.Search, contentDescription = "Ver")
                    }
                }
            }
        }

        // Botón flotante para crear un nuevo post
        FloatingActionButton(
            onClick = { navController.navigate("crear") },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = colorScheme.primary
        ) {
            Icon(imageVector = Icons.Outlined.Add, contentDescription = "Crear Post")
        }
    }
}


@Composable
fun ScreenPost(navController: NavHostController, postViewModel: PostViewModel, id: Int) {
    val post = postViewModel.postDetail.value
    LaunchedEffect(id) {
        postViewModel.obtenerPostPorId(id)
    }

    post?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text = "Detalles del Post", style = MaterialTheme.typography.titleLarge)

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = it.id.toString(),
                onValueChange = {},
                label = { Text("ID") },
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = it.userId.toString(),
                onValueChange = {},
                label = { Text("User ID") },
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = it.title,
                onValueChange = {},
                label = { Text("Título") },
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = it.body,
                onValueChange = {},
                label = { Text("Cuerpo") },
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    navController.navigate("editar/${it.id}")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Editar Post")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    postViewModel.eliminarPost(it.id)
                    navController.popBackStack() // Volver a la lista después de eliminar
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("Eliminar Post")
            }
        }
    } ?: run {
        Text("Cargando post...")
    }
}


@Composable
fun CrearPostScreen(postViewModel: PostViewModel, onPostCreated: () -> Unit) {
    val titleState = remember { mutableStateOf(TextFieldValue()) }
    val bodyState = remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = titleState.value,
            onValueChange = { titleState.value = it },
            label = { Text("Título") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = bodyState.value,
            onValueChange = { bodyState.value = it },
            label = { Text("Cuerpo") },
            modifier = Modifier.fillMaxHeight(0.3f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            // Crear un nuevo post (debes definir un ID único aquí, esto es solo un ejemplo)
            val newPost = PostModel(
                userId = 1,
                id = (postViewModel.listaPosts.size + 1), // Simula un nuevo ID
                title = titleState.value.text,
                body = bodyState.value.text
            )
            postViewModel.crearPost(newPost)
            onPostCreated()
        }) {
            Text("Crear Post")
        }
    }
}


@Composable
fun EditarPostScreen(post: PostModel, onActualizar: (PostModel) -> Unit, onNavigateBack: () -> Unit) {
    var title by remember { mutableStateOf(post.title) }
    var body by remember { mutableStateOf(post.body) }
    var isError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Editar Post",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Título") },
            isError = isError && title.isEmpty(),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = body,
            onValueChange = { body = it },
            label = { Text("Cuerpo") },
            isError = isError && body.isEmpty(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (title.isNotEmpty() && body.isNotEmpty()) {
                    val updatedPost = post.copy(title = title, body = body)
                    onActualizar(updatedPost)
                    onNavigateBack()
                } else {
                    isError = true
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar Post")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onNavigateBack,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
        ) {
            Text("Cancelar")
        }
    }
}

