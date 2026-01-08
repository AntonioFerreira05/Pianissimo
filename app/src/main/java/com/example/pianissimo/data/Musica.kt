package com.example.pianissimo.data

data class Musica(
    val id: String,
    val title: String,
    val duration: Int,
    val genre: String?,
    val play_count: Int,
    val user: User,
    val artwork: Artwork?,
)
