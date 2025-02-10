package com.example.sneakershop.ui.main.favorites

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
import com.example.domain.model.Product
import com.example.sneakershop.R
import com.example.sneakershop.ui.components.CustomLoadingIndicator
import com.example.sneakershop.ui.components.main.ProductsGrid
import com.example.sneakershop.ui.components.navbar.SneakersNavBar
import com.example.sneakershop.ui.components.topbar.SneakersTopBar
import com.example.sneakershop.ui.theme.customBackgroundColor
import com.example.sneakershop.ui.theme.customRedColor

@Composable
fun Favorites(
    viewmodel: FavoritesViewmodel,
    navigateToDetails: (Product, List<Product>) -> Unit,
    navigateBack: () -> Unit,
    navigateToScreen: (Int) -> Unit
) {
    val state by viewmodel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewmodel.getFavoriteAndCartProductsIds()
    }

    Scaffold(
        topBar = {
            SneakersTopBar(
                title = "Избранное",
                navIcon = painterResource(R.drawable.back),
                actionsIcon = painterResource(R.drawable.favorite_fill),
                actionIconTint = customRedColor,
                onNavIconClick = navigateBack
            )
        },
        bottomBar = {
            SneakersNavBar(
                initialIndexValue = 1,
                navigateToScreen = { navigateToScreen(it) }
            )
        }
    ) { contentPadding ->
        if(state.favorites.isEmpty())
            CustomLoadingIndicator(
                modifier = Modifier.padding(top = contentPadding.calculateTopPadding())
            )
        else
            ProductsGrid(
                productsList = state.favorites,
                favoriteProductsIds = state.favoriteProductsIds,
                cartProductsIds = state.cartProductsIds,
                onCardClick = { product ->
                    navigateToDetails(product, state.favorites)
                },
                onFavoriteIconClick = { viewmodel.toggleFavorite(it) },
                onButtonClick = { viewmodel.toggleCart(it) },
                modifier = Modifier
                    .background(customBackgroundColor)
                    .padding(top = contentPadding.calculateTopPadding())
                    .padding(top = 20.dp)
            )
    }
}