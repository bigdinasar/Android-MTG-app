package com.example.magicgallery.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true )
data class MagicResponse (
    @Json(name = "cards") val galleryItems: List<GalleryItem>
)
