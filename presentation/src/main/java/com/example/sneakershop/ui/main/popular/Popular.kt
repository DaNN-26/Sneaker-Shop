package com.example.sneakershop.ui.main.popular

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sneakershop.R
import com.example.sneakershop.ui.components.main.ProductsGrid
import com.example.sneakershop.ui.components.topbar.SneakersTopBar
import com.example.sneakershop.ui.theme.customAccentColor
import com.example.sneakershop.ui.theme.customBackgroundColor

@Composable
fun Popular(
    viewmodel: PopularViewmodel,
    navigateBack: () -> Unit
) {
    val state by viewmodel.state.collectAsState()

    Scaffold(
        topBar = {
            SneakersTopBar(
                title = "Популярное",
                navIcon = painterResource(R.drawable.back),
                actionsIcon = painterResource(R.drawable.favorite),
                onNavIconClick = navigateBack
            )
        }
    ) { contentPadding ->
        if(state.popularProducts.isEmpty())
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding),
            ) {
                CircularProgressIndicator(
                    color = customAccentColor
                )
            }
        else
        ProductsGrid(
            productsList = state.popularProducts,
            onCardClick = { /*TODO*/ },
            onFavoriteIconClick = { /*TODO*/ },
            onButtonClick = { /*TODO*/ },
            modifier = Modifier
                .background(customBackgroundColor)
                .padding(contentPadding)
                .padding(top = 20.dp)
        )
    }
}