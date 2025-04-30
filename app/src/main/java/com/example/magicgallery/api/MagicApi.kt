package com.example.magicgallery.api

import retrofit2.http.GET

const val SET = "KTK"

interface MagicApi {
    @GET(
        "cards?" + "set=$SET" + "&pageSize=10"
    )
    suspend fun fetchCards(): MagicResponse
}