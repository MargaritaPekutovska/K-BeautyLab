package com.margarita_pekutovskaya.k_beautylab.uiState

import com.margarita_pekutovskaya.k_beautylab.data.model.CosmeticItem

sealed class CosmeticCatalogueUIState {

    object ShowProgressIndicator : CosmeticCatalogueUIState()

    data class DataLoaded(val cosmeticItems: List<CosmeticItem>) : CosmeticCatalogueUIState()

    data class Error(val exception: Exception) : CosmeticCatalogueUIState()
}