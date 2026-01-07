package com.example.pianissimo.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pianissimo.MusicaViewModel


@Composable
fun EcraDetalheMusica(viewModel: MusicaViewModel = hiltViewModel()) {
    val musicaSelecionada by viewModel.musicaSelecionada.collectAsState()

    musicaSelecionada?.let { musica ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(text = musica.title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(text = "Autor: ${musica.user.name}", fontSize = 18.sp)
            Text(text = "Gênero: ${musica.genre ?: "Unknown"}", fontSize = 16.sp)
            Text(text = "Plays: ${musica.play_count}", fontSize = 16.sp)
            Image(
                painter = rememberAsyncImagePainter(musica.artwork?.image150),
                contentDescription = "Artwork",
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    } ?: run {
        Text("Nenhuma música selecionada", modifier = Modifier.padding(16.dp))
    }
}

