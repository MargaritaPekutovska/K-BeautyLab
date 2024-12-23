package com.margarita_pekutovskaya.k_beautylab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.margarita_pekutovskaya.k_beautylab.compose.CosmeticCatalogueScreen
import com.margarita_pekutovskaya.k_beautylab.data.CosmeticsRepository
import com.margarita_pekutovskaya.k_beautylab.data.SampleDataSource
import com.margarita_pekutovskaya.k_beautylab.ui.theme.KBeautyLabTheme
import com.margarita_pekutovskaya.k_beautylab.viewModels.CosmeticCatalogueViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel = CosmeticCatalogueViewModel(
            CosmeticsRepository(
                SampleDataSource()
            )
        )
        setContent {
            KBeautyLabTheme {
                    CosmeticCatalogueScreen(modifier = Modifier.fillMaxSize(), viewModel)
                }
            }
        }
    }


