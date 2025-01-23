package com.margarita_pekutovskaya.k_beautylab.data

import com.margarita_pekutovskaya.k_beautylab.data.client.CosmeticsApiClient
import com.margarita_pekutovskaya.k_beautylab.data.model.CosmeticItem

class RemoteDataSource(private var cosmeticsApiClient: CosmeticsApiClient) : DataSource {
    override suspend fun getCosmeticItems(): List<CosmeticItem> {

        val cosmeticsApi = cosmeticsApiClient.provideCosmeticsApi()
         return cosmeticsApi.fetchCosmeticProducts()
    }
}