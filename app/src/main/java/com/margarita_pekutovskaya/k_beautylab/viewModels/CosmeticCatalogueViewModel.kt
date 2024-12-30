package com.margarita_pekutovskaya.k_beautylab.viewModels

import com.margarita_pekutovskaya.k_beautylab.data.CosmeticsRepository
import com.margarita_pekutovskaya.k_beautylab.data.model.CosmeticItem
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.margarita_pekutovskaya.k_beautylab.data.SampleDataSource

class CosmeticCatalogueViewModel(
    private val cosmeticsRepository: CosmeticsRepository
) : ViewModel() {

    fun getCosmeticItems(): List<CosmeticItem> {
        return cosmeticsRepository.getCosmeticItems()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val repository = CosmeticsRepository(dataSource = SampleDataSource())
                return CosmeticCatalogueViewModel(
                    repository
                ) as T
            }

        }
    }
}