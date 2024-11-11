package MVVM.View

import MVVM.MODEL.Amphibian
import MVVM.ViewModel.AmphibianViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color // Asegúrate de importar Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmphibianListScreen(viewModel: AmphibianViewModel) {
    val amphibians by viewModel.amphibians.observeAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Amphibians") })
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(amphibians) { amphibian ->
                AmphibianCard(amphibian)
            }
        }
    }
}

@Composable
fun AmphibianCard(amphibian: Amphibian) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { /* Navigate to detail */ },
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFD3F0D4) // Cambia este color al que prefieras
        )
    ) {
        Column {
            Image(
                painter = rememberImagePainter(amphibian.img_src),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth() // Asegura que la imagen llene todo el ancho de la tarjeta
                    .height(180.dp), // Ajusta la altura según sea necesario
                contentScale = ContentScale.Crop // Recorta la imagen si es necesario
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.padding(16.dp)) {
                Column {
                    Text(amphibian.name, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Text(amphibian.type)
                    Text(amphibian.description)
                }
            }
        }
    }
}