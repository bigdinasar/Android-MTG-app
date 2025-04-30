package com.example.magicgallery

data class MtgCardForeignName(
    val name: String,
    val imageUrl: String?,
    val language: String,
    val multiverseid: Int?
)