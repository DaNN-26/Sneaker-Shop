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
    onCardClick: (Product) -> Unit,
    onFavoriteIconClick: () -> Unit,
    onButtonClick: () -> Unit,
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
                onCardClick = { onCardClick(it) },
                onFavoriteIconClick = onFavoriteIconClick,
                onButtonClick = onButtonClick
            )
        }
    }
}