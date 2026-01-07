package com.example.pianissimo.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil3.compose.rememberAsyncImagePainter
import com.example.pianissimo.Destino
import com.example.pianissimo.MusicaViewModel
import com.example.pianissimo.data.Musica

@Composable
fun MusicaComposable(
    navController: NavController,
    viewModel: MusicaViewModel = hiltViewModel()
) {
    val tracks = viewModel.getMusica().collectAsLazyPagingItems()
    val contexto = LocalContext.current

    LazyColumn {
        items(
            count = tracks.itemCount,
            key = tracks.itemKey { it.id },
            contentType = tracks.itemContentType { "track" }
        ) { index ->
            val item = tracks[index]
            item?.let { musica ->
                CardMusica(
                    musica = musica,
                    onClick = {
                        viewModel.selecionarMusica(musica)
                        navController.navigate(Destino.Ecra02.route)
                    }
                )
            }
        }

        // Loading / Erro do refresh
        when (tracks.loadState.refresh) {
            is LoadState.Error -> {
                Toast.makeText(
                    contexto,
                    "Erro ao atualizar: ${(tracks.loadState.refresh as LoadState.Error).error}",
                    Toast.LENGTH_LONG
                ).show()
            }
            is LoadState.Loading -> {
                item {
                    Column(
                        modifier = Modifier.fillParentMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(modifier = Modifier.padding(8.dp), text = "Carregando músicas...")
                        CircularProgressIndicator(color = Color.Black)
                    }
                }
            }
            else -> {}
        }

        // Loading / Erro da paginação
        when (tracks.loadState.append) {
            is LoadState.Error -> {
                Toast.makeText(
                    contexto,
                    "Erro ao carregar mais: ${(tracks.loadState.append as LoadState.Error).error}",
                    Toast.LENGTH_LONG
                ).show()
            }
            is LoadState.Loading -> {
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Carregando mais músicas...")
                        CircularProgressIndicator(color = Color.Black)
                    }
                }
            }
            else -> {}
        }
    }
}

@Composable
fun CardMusica(
    musica: Musica,
    onClick: (Musica) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(10.dp)
            .clickable { onClick(musica) },
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape),
                painter = rememberAsyncImagePainter(musica.artwork?.image150),
                contentScale = ContentScale.Crop,
                contentDescription = "Artwork"
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = musica.user.name, color = Color.Black)
                Text(
                    text = "Gênero: ${musica.genre ?: "Unknown"} | Plays: ${musica.play_count ?: 0}",
                    color = Color.Black
                )
                Text(text = musica.title, color = Color.Black)
            }
        }
    }
}
