package com.margarita_pekutovskaya.k_beautylab.compose

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.margarita_pekutovskaya.k_beautylab.data.model.CosmeticItem
import com.margarita_pekutovskaya.k_beautylab.viewModels.CosmeticCatalogueViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.margarita_pekutovskaya.k_beautylab.R
import com.margarita_pekutovskaya.k_beautylab.uiState.CosmeticCatalogueUIState

@Composable
fun CosmeticCatalogueScreen(
    modifier: Modifier = Modifier,
    viewModel: CosmeticCatalogueViewModel = viewModel(factory = CosmeticCatalogueViewModel.Factory)
) {
    val uiState: CosmeticCatalogueUIState = viewModel.uiState
    when (uiState) {
        is CosmeticCatalogueUIState.ShowProgressIndicator -> {
            CircularProgressDemo()
        }

        is CosmeticCatalogueUIState.Error -> {
            ShowErrorToast(message = stringResource(R.string.error_message))
        }

        is CosmeticCatalogueUIState.DataLoaded -> {
            val cosmeticItems: List<CosmeticItem> = uiState.cosmeticItems
            LazyColumn(modifier = modifier) {
                items(cosmeticItems) {
                    CosmeticCatalogueItem(item = it)
                }
            }
        }
    }
}

@Composable
fun CircularProgressDemo() {
    CircularProgressIndicator(
        progress = { 0.7f },
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        color = Color.Gray,
        strokeWidth = 4.dp,
    )
}

@Composable
fun ShowErrorToast(message: String) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
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