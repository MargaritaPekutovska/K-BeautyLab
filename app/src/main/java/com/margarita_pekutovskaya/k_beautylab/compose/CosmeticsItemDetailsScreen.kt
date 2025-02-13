package com.margarita_pekutovskaya.k_beautylab.compose

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.rememberAsyncImagePainter
import com.margarita_pekutovskaya.k_beautylab.R
import com.margarita_pekutovskaya.k_beautylab.viewModels.CosmeticCatalogueViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CosmeticsItemDetailsScreen(
    onNavigateBack: () -> Unit,
    viewModel: CosmeticCatalogueViewModel = viewModel(factory = CosmeticCatalogueViewModel.Factory)
) {
    val selectedItem = viewModel.selectedCosmeticItem

    selectedItem?.let { cosmeticItem ->
        Scaffold(
            topBar = {
                TopAppBar(
                    modifier = Modifier.shadow(elevation = 5.dp),
                    title = {
                        Text(
                            text = stringResource(id = R.string.top_app_bar_title),
                            fontFamily = FontFamily(Font(R.font.cabin_variable_font_wght))
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = colorResource(id = R.color.button_color),
                        titleContentColor = Color.White
                    ),
                    navigationIcon = {
                        IconButton(onClick = onNavigateBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                tint = Color.White,
                                contentDescription = stringResource(id = R.string.top_bar_details_content_description)
                            )
                        }
                    }
                )
            },
            content = { padding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(59.dp)
                        .background(brush = getGradientBrush())
                        .padding(padding)
                        .padding(16.dp)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(model = cosmeticItem.imageLink),
                        contentDescription = cosmeticItem.name,
                        modifier = Modifier
                            .shadow(elevation = 10.dp)
                            .fillMaxWidth()
                            .height(250.dp)
                            .background(Color.White)
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = cosmeticItem.name,
                        fontFamily = FontFamily(Font(R.font.cabin_variable_font_wght)),
                        fontSize = 28.sp,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    var isExpanded by remember { mutableStateOf(false) }

                    Box(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
                        Column {
                            Text(
                                text = if (isExpanded) cosmeticItem.description else cosmeticItem.description.take(
                                    100
                                ) + "...",
                                style = MaterialTheme.typography.bodyMedium,
                                fontSize = 18.sp,
                                maxLines = if (isExpanded) Int.MAX_VALUE else 3
                            )
                            Spacer(modifier = Modifier.padding(6.dp))
                            Text(
                                text = if (isExpanded) stringResource(id = R.string.top_bar_content_text1)
                                else stringResource(id = R.string.top_bar_content_text2),
                                color = colorResource(id = R.color.dark_pink),
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                        Spacer(modifier = Modifier.padding(20.dp))
                    }

                    Button(
                        onClick = {},
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .height(40.dp)
                            .align(Alignment.CenterHorizontally),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.button_color),
                            contentColor = colorResource(id = R.color.white)
                        )
                    ) {
                        Text(text = stringResource(id = R.string.button_details_text))
                    }
                }
            }
        )

    } ?: run {
        Text(
            stringResource(id = R.string.run_text),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
    }

    BackHandler {
        onNavigateBack()
    }
}