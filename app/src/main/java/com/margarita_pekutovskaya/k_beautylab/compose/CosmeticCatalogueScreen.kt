package com.margarita_pekutovskaya.k_beautylab.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.margarita_pekutovskaya.k_beautylab.viewModels.CosmeticCatalogueViewModel

@Composable
fun CosmeticCatalogueScreen(
    modifier: Modifier = Modifier,
    viewModel: CosmeticCatalogueViewModel
) {
    val cosmeticItems = remember { viewModel.getCosmeticItems() }
}