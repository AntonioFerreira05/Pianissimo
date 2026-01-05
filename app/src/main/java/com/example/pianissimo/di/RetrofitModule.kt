package com.example.pianissimo.di

import com.example.pianissimo.data.service.MusicaApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    private val BASE_URL = "https://api.audius.co/v1/"

    @Singleton
    @Provides
    fun provideRetrofitInstance(): MusicaApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MusicaApiService::class.java)
}
