package com.margarita_pekutovskaya.k_beautylab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.margarita_pekutovskaya.k_beautylab.compose.CosmeticsCatalogueScreen
import com.margarita_pekutovskaya.k_beautylab.compose.CosmeticsItemDetailsScreen
import com.margarita_pekutovskaya.k_beautylab.compose.Screen
import com.margarita_pekutovskaya.k_beautylab.ui.theme.KBeautyLabTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KBeautyLabTheme {
                var currentScreen: Screen by remember { mutableStateOf(Screen.COSMETICS_CATALOGUE) }
                when (currentScreen) {

                    Screen.COSMETICS_CATALOGUE -> {
                        CosmeticsCatalogueScreen(
                            modifier = Modifier.fillMaxSize(),
                            onNavigateToDetails = {
                                currentScreen = Screen.SCREEN_DETAILS
                            }
                        )
                    }

                    Screen.SCREEN_DETAILS -> {
                        CosmeticsItemDetailsScreen(
                            onNavigateBack = {
                                currentScreen = Screen.COSMETICS_CATALOGUE
                            }
                        )
                    }
                }
            }
        }
    }
}