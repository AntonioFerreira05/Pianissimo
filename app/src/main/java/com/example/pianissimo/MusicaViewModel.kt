package com.example.pianissimo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pianissimo.data.Musica
import com.example.pianissimo.data.repository.MusicaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MusicaViewModel @Inject constructor(
    private val repository: MusicaRepository
) : ViewModel() {
    fun getMusica(): Flow<PagingData<Musica>> = repository.getMusica().cachedIn(viewModelScope)
}
