package com.example.pianissimo.data

import com.google.gson.annotations.SerializedName

data class Artwork(
    @SerializedName("150x150")
    val image150: String?
)
