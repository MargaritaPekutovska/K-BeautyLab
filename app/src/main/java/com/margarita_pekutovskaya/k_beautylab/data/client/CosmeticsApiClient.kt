package com.margarita_pekutovskaya.k_beautylab.data.client

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CosmeticsApiClient {
    private val BASE_URL = "http://makeup-api.herokuapp.com/"

    private fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
    }

    fun provideCosmeticsApi(): CosmeticsApi {
        val retrofit = getInstance()
        return retrofit.create(CosmeticsApi::class.java)
    }
}
