package com.margarita_pekutovskaya.k_beautylab.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import com.margarita_pekutovskaya.k_beautylab.R

@Composable
fun getGradientBrush(): Brush {
    return Brush.verticalGradient(
        colors = listOf(
            colorResource(id = R.color.dark_pink),
            colorResource(id = R.color.light_pink),
            colorResource(id = R.color.white),
            colorResource(id = R.color.light_green1)
        )
    )
}