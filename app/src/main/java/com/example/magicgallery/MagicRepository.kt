package com.example.magicgallery

import android.util.Log
import com.example.magicgallery.api.GalleryItem
import com.example.magicgallery.api.MagicApi
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create

const private val TAG = "MagicRepository"

class MagicRepository {
    private val magicApi: MagicApi

    init {
        Log.d(TAG, "Repository launching")
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.magicthegathering.io/v1/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        magicApi = retrofit.create()
        Log.d(TAG, "magicApi created:")
    }

    suspend fun fetchCards(): List<GalleryItem> =
        magicApi.fetchCards().galleryItems

}