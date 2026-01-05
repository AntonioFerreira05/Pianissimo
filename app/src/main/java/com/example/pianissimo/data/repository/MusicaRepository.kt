package com.example.pianissimo.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.pianissimo.data.Musica
import com.example.pianissimo.data.pager.MusicaPagingSource
import com.example.pianissimo.data.service.MusicaApiService
import javax.inject.Inject

class MusicaRepository @Inject constructor(private val musicaApiService: MusicaApiService) {
    fun getMusica() = Pager(config = PagingConfig(pageSize = 20), pagingSourceFactory = { MusicaPagingSource(musicaApiService) }).flow
}