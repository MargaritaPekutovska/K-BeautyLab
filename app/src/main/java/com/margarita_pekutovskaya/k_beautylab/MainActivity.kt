package com.margarita_pekutovskaya.k_beautylab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.margarita_pekutovskaya.k_beautylab.compose.CosmeticCatalogueScreen
import com.margarita_pekutovskaya.k_beautylab.ui.theme.KBeautyLabTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            KBeautyLabTheme {
                CosmeticCatalogueScreen(
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            }
        }
    }