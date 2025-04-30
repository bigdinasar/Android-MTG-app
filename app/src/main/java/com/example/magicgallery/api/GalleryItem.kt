package com.example.magicgallery.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GalleryItem (
    val name: String,
    val multiverseid: String,
    @Json(name = "imageUrl") val url: String,
    val cmc: String,
    val type: String,
    val rarity: String

)