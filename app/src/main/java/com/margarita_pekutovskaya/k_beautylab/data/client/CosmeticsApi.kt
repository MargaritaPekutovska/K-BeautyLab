package com.margarita_pekutovskaya.k_beautylab.data.client


import com.margarita_pekutovskaya.k_beautylab.data.model.CosmeticItem
import retrofit2.Call
import retrofit2.http.GET

interface CosmeticsApi {
    @GET("api/v1/products.json?brand=covergirl&product_type=lipstick")
   suspend fun fetchCosmeticProducts(): List<CosmeticItem>
}