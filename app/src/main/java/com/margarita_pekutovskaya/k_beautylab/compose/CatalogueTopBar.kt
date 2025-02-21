package com.margarita_pekutovskaya.k_beautylab.compose

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.margarita_pekutovskaya.k_beautylab.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogueTopBar(
    @StringRes titleText: Int,
    actions: @Composable RowScope.() -> Unit = {},
    navigationIcon: @Composable () -> Unit = {},
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(titleText),
                fontFamily = FontFamily(Font(R.font.cabin_variable_font_wght))
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(id = R.color.button_color),
            titleContentColor = Color.White
        ),
        modifier = Modifier.shadow(elevation = 5.dp),
        actions = actions,
        navigationIcon = navigationIcon
    )
}