package com.veryable.android.rest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {

    // AWS URL
    private const val BASE_URL =
        "https://veryable-public-assets.s3.us-east-2.amazonaws.com"

    // Gson converter factory for Retrofit
    private val gsonConverterFactory = GsonConverterFactory.create()

    // The single instance of Retrofit
    val retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(gsonConverterFactory).build()

}