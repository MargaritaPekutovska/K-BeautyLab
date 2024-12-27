package com.margarita_pekutovskaya.k_beautylab.data

import com.margarita_pekutovskaya.k_beautylab.data.model.CosmeticItem

class CosmeticsRepository(
    private val dataSource: DataSource
) {

    fun getCosmeticItems(): List<CosmeticItem> {
        return dataSource.getCosmeticItems()
    }
}