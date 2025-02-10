package com.example.sneakershop.ui.components.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.domain.model.Product
import com.example.sneakershop.ui.components.product.ProductItem
import com.example.sneakershop.ui.theme.customBackgroundColor

@Composable
fun ProductsGrid(
    productsList: List<Product>,
    favoriteProductsIds: Set<Int>,
    cartProductsIds: Set<Int>,
    onCardClick: (Product) -> Unit,
    onFavoriteIconClick: (Product) -> Unit,
    onButtonClick: (Product) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        verticalArrangement = Arrangement.spacedBy(
            15.dp, Alignment.Top
        ),
        horizontalArrangement = Arrangement.spacedBy(
            15.dp, Alignment.CenterHorizontally
        ),
        contentPadding = PaddingValues(horizontal = 20.dp),
        columns = GridCells.Fixed(2),
        modifier = modifier
            .background(customBackgroundColor)
            .fillMaxSize()
    ) {
        items(productsList) { product ->
            ProductItem(
                product = product,
                isFavorite = favoriteProductsIds.contains(product.id),
                isInCart = cartProductsIds.contains(product.id),
                onCardClick = { onCardClick(product) },
                onFavoriteIconClick = { onFavoriteIconClick(product) },
                onButtonClick = { onButtonClick(product) }
            )
        }
    }
}