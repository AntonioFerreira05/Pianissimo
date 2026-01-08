package com.example.pianissimo.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pianissimo.MusicaViewModel

@Composable
fun Streamer(viewModel: MusicaViewModel = hiltViewModel()) {
    val musicaSelecionada by viewModel.musicaSelecionada.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        musicaSelecionada?.let { musica ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(text = musica.title, fontSize = 24.sp)
                Text(text = "Autor: ${musica.user.name}", fontSize = 18.sp)
                Text(text = "Gênero: ${musica.genre ?: "Unknown"}", fontSize = 16.sp)
                Text(text = "Plays: ${musica.play_count}", fontSize = 16.sp)

                val streamUrl = "https://api.audius.co/v1/tracks/${musica.id}/stream"

                Spacer(modifier = Modifier.height(16.dp))
                MusicPlayer(url = streamUrl)

            }
        } ?: run {
            Text("Nenhuma música selecionada", modifier = Modifier.padding(16.dp))
        }
    }
}
