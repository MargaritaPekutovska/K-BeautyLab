package com.margarita_pekutovskaya.k_beautylab.viewModels

import com.margarita_pekutovskaya.k_beautylab.data.CosmeticsRepository
import com.margarita_pekutovskaya.k_beautylab.data.model.CosmeticItem

class CosmeticCatalogueViewModel(private val cosmeticsRepository: CosmeticsRepository) {

    fun getCosmeticItems():List<CosmeticItem> {
        return cosmeticsRepository.getCosmeticItems()
    }
}