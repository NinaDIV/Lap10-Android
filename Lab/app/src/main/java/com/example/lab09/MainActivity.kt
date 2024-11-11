package com.example.lab09

import MVVM.API.PostApiService
import MVVM.View.CrearPostScreen
import MVVM.View.EditarPostScreen
import MVVM.View.ScreenPost
import MVVM.View.ScreenPosts
import MVVM.ViewModel.PostViewModel
import PostViewModelFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lab09.ui.theme.Lab09Theme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val servicio = retrofit.create(PostApiService::class.java)
        val viewModelFactory = PostViewModelFactory(servicio)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(PostViewModel::class.java)

        setContent {
            ProgPrincipal9(viewModel) // Pasa el ViewModel a tu Composable
        }
            }
        }






@Composable
fun ProgPrincipal9(viewModel: PostViewModel) {
    val urlBase = "https://jsonplaceholder.typicode.com/"
    val retrofit = Retrofit.Builder()
        .baseUrl(urlBase)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val servicio = retrofit.create(PostApiService::class.java)

    val postViewModel = viewModel<PostViewModel>(factory = PostViewModelFactory(servicio))

    val navController = rememberNavController()

    Scaffold(
        topBar = { BarraSuperior() },
        bottomBar = { BarraInferior(navController) },
        content = { paddingValues -> Contenido(paddingValues, navController, postViewModel) }
    )
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperior() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "JSONPlaceHolder Access",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    )
}

@Composable
fun BarraInferior(navController: NavHostController) {
    NavigationBar(
        containerColor = Color.LightGray
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Outlined.Home, contentDescription = "Inicio") },
            label = { Text("Inicio") },
            selected = navController.currentDestination?.route == "inicio",
            onClick = { navController.navigate("inicio") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Outlined.Favorite, contentDescription = "Posts") },
            label = { Text("Posts") },
            selected = navController.currentDestination?.route == "posts",
            onClick = { navController.navigate("posts") }
        )
    }
}





@Composable
fun Contenido(
    paddingValues: PaddingValues,
    navController: NavHostController,
    postViewModel: PostViewModel
) {
    Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
        NavHost(navController = navController, startDestination = "inicio") {
            composable("inicio") { ScreenInicio() }
            composable("posts") {
                ScreenPosts(navController, postViewModel)
            }
            composable("postsVer/{id}", arguments = listOf(navArgument("id") { type = NavType.IntType })) {
                ScreenPost(navController, postViewModel, it.arguments!!.getInt("id"))
            }
            composable("crear") {
                CrearPostScreen(postViewModel) {
                    navController.popBackStack() // Regresar a la lista de posts después de crear
                }
            }
            composable("editar/{id}", arguments = listOf(navArgument("id") { type = NavType.IntType })) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("id") ?: return@composable
                val post = postViewModel.listaPosts.find { it.id == id }
                if (post != null) {
                    EditarPostScreen(post, { updatedPost -> postViewModel.actualizarPost(id, updatedPost) }, { navController.popBackStack() })
                }
            }
        }
    }
}



@Composable
fun ScreenInicio() {
    Text("INICIO")
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab09Theme {
        ProgPrincipal9(viewModel())

    }
}