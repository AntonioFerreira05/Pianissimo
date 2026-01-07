package com.example.pianissimo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pianissimo.data.Musica
import com.example.pianissimo.data.repository.MusicaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MusicaViewModel @Inject constructor(
    private val repository: MusicaRepository
) : ViewModel() {

    private val _musicaSelecionada = MutableStateFlow<Musica?>(null)
    val musicaSelecionada: StateFlow<Musica?> = _musicaSelecionada

    fun selecionarMusica(musica: Musica) {
        _musicaSelecionada.value = musica
    }

    fun getMusica() = repository.getMusica()
}
