package com.margarita_pekutovskaya.k_beautylab.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.margarita_pekutovskaya.k_beautylab.data.CosmeticsRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.margarita_pekutovskaya.k_beautylab.data.RemoteDataSource
import com.margarita_pekutovskaya.k_beautylab.data.client.CosmeticsApiClient
import com.margarita_pekutovskaya.k_beautylab.uiState.CosmeticCatalogueUIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CosmeticCatalogueViewModel(
    private val cosmeticsRepository: CosmeticsRepository
) : ViewModel() {

    var uiState by mutableStateOf<CosmeticCatalogueUIState>(CosmeticCatalogueUIState.ShowProgressIndicator)
        private set

    init {
        fetchCosmeticItems()
    }

    fun fetchCosmeticItems() {
        viewModelScope.launch {
            delay(2000) // temporary for testing progress indicator
            fetchCosmeticInternally()
        }
    }

    fun performSearch() {

    }

    private suspend fun fetchCosmeticInternally() {
        try {
            val cosmeticItems = cosmeticsRepository.getCosmeticItems()
            uiState = CosmeticCatalogueUIState.DataLoaded(cosmeticItems)
        } catch (exception: Exception) {
            uiState = CosmeticCatalogueUIState.Error(exception)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val remoteDataSource = RemoteDataSource(CosmeticsApiClient())
                val repository = CosmeticsRepository(dataSource = remoteDataSource)
                return CosmeticCatalogueViewModel(
                    repository
                ) as T
            }

        }
    }
}