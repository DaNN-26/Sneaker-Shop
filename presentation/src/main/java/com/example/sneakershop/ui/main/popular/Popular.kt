package com.example.sneakershop.ui.main.popular

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.domain.supabase.model.Product
import com.example.sneakershop.R
import com.example.sneakershop.ui.components.CustomLoadingIndicator
import com.example.sneakershop.ui.components.main.ProductsGrid
import com.example.sneakershop.ui.components.topbar.SneakersTopBar
import com.example.sneakershop.ui.theme.customBackgroundColor

@Composable
fun Popular(
    viewmodel: PopularViewmodel,
    navigateToDetails: (Product, List<Product>) -> Unit,
    navigateBack: () -> Unit
) {
    val state by viewmodel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewmodel.getFavoriteAndProductsIds()
    }

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
            CustomLoadingIndicator(
                modifier = Modifier.padding(contentPadding)
            )
        else
            ProductsGrid(
                productsList = state.popularProducts,
                favoriteProductsIds = state.favoriteProductsIds,
                cartProductsIds = state.cartProductsIds,
                onCardClick = { product ->
                    navigateToDetails(product, state.popularProducts)
                },
                onFavoriteIconClick = { viewmodel.toggleFavorite(it) },
                onButtonClick = { viewmodel.toggleCart(it) },
                modifier = Modifier
                    .background(customBackgroundColor)
                    .padding(contentPadding)
                    .padding(top = 20.dp)
            )
    }
}