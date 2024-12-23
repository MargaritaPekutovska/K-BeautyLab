package com.margarita_pekutovskaya.k_beautylab.data

import com.margarita_pekutovskaya.k_beautylab.data.model.CosmeticItem

interface DataSource {
    fun getCosmeticItems():List<CosmeticItem>
}