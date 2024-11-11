package com.example.anfibios

import MVVM.API.ApiService
import MVVM.MODEL.Amphibian
import MVVM.Repository.AmphibianRepository
import MVVM.View.AmphibianListScreen
import MVVM.ViewModel.AmphibianViewModel
import MVVM.ViewModel.AmphibianViewModelFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.anfibios.ui.theme.AnfibiosTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnfibiosTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val apiService = remember { Retrofit.Builder()
        .baseUrl("https://android-kotlin-fun-mars-server.appspot.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java) }

    val repository = remember { AmphibianRepository(apiService) }
    val viewModelFactory = AmphibianViewModelFactory(repository)
    val amphibianViewModel: AmphibianViewModel = viewModel(factory = viewModelFactory)

    AmphibianListScreen(amphibianViewModel)
}