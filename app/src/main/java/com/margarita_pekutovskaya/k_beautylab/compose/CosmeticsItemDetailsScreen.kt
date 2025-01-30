package com.margarita_pekutovskaya.k_beautylab.compose

import androidx.activity.compose.BackHandler
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun CosmeticsItemDetailsScreen(
    onNavigateBack: () -> Unit
){
    Text(
        text = "Cosmetics Item Details Screen",
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal,
        textAlign = TextAlign.Center,
    )
    BackHandler {
        onNavigateBack()
    }
}