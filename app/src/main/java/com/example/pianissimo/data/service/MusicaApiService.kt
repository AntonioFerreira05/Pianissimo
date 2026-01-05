package com.example.pianissimo.data.service

import com.example.pianissimo.data.MusicaResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicaApiService {

    @GET("tracks/trending")
    suspend fun getTrendingTracks(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0,
        @Query("time") time: String = "week" // day, week, month, year, allTime
    ): MusicaResponse
}
