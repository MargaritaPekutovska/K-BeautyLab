@file:OptIn(ExperimentalMaterial3Api::class)

package com.margarita_pekutovskaya.k_beautylab.compose

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.margarita_pekutovskaya.k_beautylab.R
import com.margarita_pekutovskaya.k_beautylab.data.model.CosmeticItem
import com.margarita_pekutovskaya.k_beautylab.ui.theme.KBeautyLabTheme
import com.margarita_pekutovskaya.k_beautylab.uiState.CosmeticCatalogueUIState
import com.margarita_pekutovskaya.k_beautylab.viewModels.CosmeticCatalogueViewModel

@Composable
fun CosmeticsCatalogueScreen(
    modifier: Modifier,
    viewModel: CosmeticCatalogueViewModel = viewModel(factory = CosmeticCatalogueViewModel.Factory)
) {
    var isSearchActive by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            if (isSearchActive) {
                SearchBarPanel(
                    onBackClick = { isSearchActive = false },
                    viewModel = viewModel
                )
            } else {
                CatalogueTopBar(onSearchClick = { isSearchActive = true })
            }
        }
    )
    { innerPadding ->
        CosmeticsCatalogueContent(
            innerPadding = innerPadding,
            viewModel = viewModel,
            modifier = modifier,
        )
    }
}

@Composable
private fun SearchBarPanel(
    onBackClick: () -> Unit,
    viewModel: CosmeticCatalogueViewModel
) {
    var searchQuery by remember { mutableStateOf("") }

    SearchBar(
        query = searchQuery,
        onQueryChange = { newQuery ->
            searchQuery = newQuery
        },
        onSearch = {
            viewModel.performSearch(searchQuery)
        },
        active = true,
        onActiveChange = {},
        placeholder = {
            Text(text = stringResource(R.string.toolbar_search_items))
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                tint = MaterialTheme.colorScheme.onSurface,
                contentDescription = null
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Close,
                tint = MaterialTheme.colorScheme.onSurface,
                contentDescription = null,
                modifier = Modifier.clickable {
                    onBackClick.invoke()
                }
            )
        },
        tonalElevation = 0.dp,
        content = {
            CosmeticsCatalogueContent(
                innerPadding = PaddingValues(all = 4.dp),
                viewModel = viewModel,
            )
        }
    )
}

@Composable
private fun CatalogueTopBar(onSearchClick: () -> Unit) {
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.toolbar_description_catalogue))
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.DarkGray,
            titleContentColor = Color.White
        ),
        actions = {
            IconButton(onClick = onSearchClick) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    tint = Color.White,
                    contentDescription = stringResource(R.string.toolbar_icon_description)
                )
            }
        }
    )
}

@Composable
private fun CosmeticsCatalogueContent(
    innerPadding: PaddingValues,
    viewModel: CosmeticCatalogueViewModel,
    modifier: Modifier = Modifier,
) {
    when (val uiState: CosmeticCatalogueUIState = viewModel.uiState) {
        is CosmeticCatalogueUIState.ShowProgressIndicator -> {
            ShowProgressIndicator()
        }

        is CosmeticCatalogueUIState.Error -> {
            ShowErrorMessage(
                onRetryClick = { viewModel.fetchCosmeticItems() }
            )
        }

        is CosmeticCatalogueUIState.DataLoaded -> {
            val cosmeticItems: List<CosmeticItem> = uiState.cosmeticItems
            LazyColumn(modifier = modifier.padding(innerPadding)) {
                items(cosmeticItems) {
                    CosmeticsCatalogueItem(item = it, onItemClick = {
                        Log.d("Details", "Item : ${it.name} is clicked")
                    })
                }
            }
        }
    }
}

@Composable
private fun ShowProgressIndicator() {
    CircularProgressIndicator(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        color = Color.Gray,
        strokeWidth = 4.dp,
    )
}

@Composable
private fun ShowErrorMessage(
    onRetryClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_error_message),
            contentDescription = "Error message",
            tint = Color.Gray,
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(id = R.string.ops_error),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray,
            textAlign = TextAlign.Center,
        )

        Text(
            text = stringResource(id = R.string.error_message),
            fontSize = 18.sp,
            style = MaterialTheme.typography.displayMedium,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onRetryClick,
            modifier = Modifier.fillMaxWidth(0.5f)
        ) {
            Text(text = stringResource(id = R.string.try_again))
        }
    }
}

@Composable
private fun CosmeticsCatalogueItem(
    modifier: Modifier = Modifier,
    item: CosmeticItem,
    onItemClick: (CosmeticItem) -> Unit
) {
    Row(
        modifier = modifier
            .padding(12.dp)
            .clickable { onItemClick(item) }
    ) {
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
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                fontSize = 14.sp,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowErrorMessagePreview(){
    KBeautyLabTheme {
        ShowErrorMessage(
            onRetryClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CosmeticCatalogueItemPreview(){
    KBeautyLabTheme {
        CosmeticsCatalogueItem(
            item = CosmeticItem(
                name = "Toner",
                imageLink = "https://media.douglas.de/medias/pJN9iQ1103976-0-global.png?context=bWFzdGVyfGltYWdlc3wyODE5Mzl8aW1hZ2UvcG5nfGFHRmxMMmd5TkM4MU1EUTFPVGs1TmpVMU16STBOaTl3U2s0NWFWRXhNVEF6T1RjMlh6QmZaMnh2WW1Gc0xuQnVad3wyOWRjODM0NDlmMzRlMjg3YjY4NTgyZmE4ZGQ4OTE0YWY1OGE4ZTk4YzY5MzRhYzg4OGU0NDEwNGZmMWFlMDdk&grid=true&transparent=true&imPolicy=grayScaledtransparent&imdensity=1&imwidth=775",
                description = "Toner with centella for problem skin."
            ),
            onItemClick = {}
        )
    }
}