package com.margarita_pekutovskaya.k_beautylab.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.margarita_pekutovskaya.k_beautylab.data.model.CosmeticItem
import com.margarita_pekutovskaya.k_beautylab.viewModels.CosmeticCatalogueViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CosmeticCatalogueScreen(
    modifier: Modifier = Modifier,
    viewModel: CosmeticCatalogueViewModel = viewModel(factory = CosmeticCatalogueViewModel.Factory)
) {
    val cosmeticItems: List<CosmeticItem> = remember { viewModel.getCosmeticItems() }
    LazyColumn(modifier = modifier) {
        items(cosmeticItems) {
            CosmeticCatalogueItem(item = it)
        }
    }
}

@Composable
private fun CosmeticCatalogueItem(
    modifier: Modifier = Modifier,
    item: CosmeticItem,
) {
    Row(modifier = modifier.padding(12.dp)) {
        AsyncImage(
            model = item.imageLink,
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)

        )

        Column(modifier = modifier.padding(start = 12.dp)) {
            Text(
                text = item.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = item.description,
                fontSize = 14.sp,
            )
        }
    }
}