package com.example.pianissimo.di

import com.example.pianissimo.data.repository.MusicaRepository
import com.example.pianissimo.data.service.MusicaApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class HiltModule {

    @Provides
    fun provideMusicaRepository(
        musicaApiService: MusicaApiService
    ): MusicaRepository = MusicaRepository(musicaApiService)
}
