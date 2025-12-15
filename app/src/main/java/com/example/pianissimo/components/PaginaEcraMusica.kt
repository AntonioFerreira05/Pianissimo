package com.example.pianissimo.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil3.compose.rememberAsyncImagePainter
import com.example.pianissimo.MusicaViewModel

@Composable
fun NoticiasComposable() {
    val viewModel = hiltViewModel<MusicaViewModel>()
    val articles = viewModel.getMusica().collectAsLazyPagingItems()
    val oContexto = LocalContext.current
    LazyColumn {
        items(count = articles.itemCount, key = articles.itemKey { it.url }, contentType = articles.itemContentType { "avsContentType" }) { index ->
            val item = articles[index]
            CardMusica(author = item?.author ?: "", content = item?.content ?: "",
                title = item?.title ?: "", urlToImage = item?.urlToImage ?: "")
        }
        when (articles.loadState.refresh) {
            is LoadState.Error -> {
                // fixme: falta reagir ao erro
                Toast.makeText(oContexto, "Erro no LoadState refresh: ${(articles.loadState.refresh as LoadState.Error).error.toString()}", Toast.LENGTH_LONG).show()
            }
            is LoadState.Loading -> {
                item {
                    Column(modifier = Modifier.fillParentMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center) {
                        Text(modifier = Modifier.padding(8.dp), text = "Refresh Loading")
                        CircularProgressIndicator(color = Color.Black)
                    }
                }
            }
            else -> {}
        }
        when (articles.loadState.append) {
            is LoadState.Error -> {
                // fixme: falta reagir ao erro
                Toast.makeText(oContexto, "Erro no LoadState append: ${(articles.loadState.append as LoadState.Error).error.toString()}", Toast.LENGTH_LONG).show()
            }
            is LoadState.Loading -> {
                item {
                    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center,
                    ) {
                        Text(text = "Pagination Loading")
                        CircularProgressIndicator(color = Color.Black)
                    }
                }
            }
            else -> {}
        }
    }
}

@Composable
fun CardMusica(author: String, content: String, title: String, urlToImage: String) {
    Card(modifier = Modifier.fillMaxWidth().padding(top = 10.dp, start = 12.dp, bottom = 2.dp, end = 12.dp).size(180.dp).clickable {},
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = RoundedCornerShape(corner = CornerSize(8.dp))
    ) {
        Row(modifier = Modifier.padding(8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(modifier = Modifier.padding(start = 16.dp).size(60.dp).clip(CircleShape),
                painter = rememberAsyncImagePainter(urlToImage), contentScale = ContentScale.Crop, contentDescription = "profile")
            Column(modifier = Modifier.padding(start = 12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = author, color = Color.Black)
                Text(text = content, modifier = Modifier.wrapContentSize(), color = Color.Black)
                Text(text = title,color = Color.Black)
            }
        }
    }
}