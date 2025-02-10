package com.example.sneakershop.ui.main.catalogue

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import com.example.domain.model.ProductCategory
import com.example.sneakershop.R
import com.example.sneakershop.ui.components.CustomLoadingIndicator
import com.example.sneakershop.ui.components.main.CategoriesRow
import com.example.sneakershop.ui.components.main.ProductsGrid
import com.example.sneakershop.ui.components.topbar.SneakersTopBar
import com.example.sneakershop.ui.theme.customBackgroundColor

@Composable
fun Catalogue(
    viewmodel: CatalogueViewmodel,
    category: ProductCategory,
    navigateToDetails: (Product, List<Product>) -> Unit,
    navigateBack: () -> Unit
) {
    val state by viewmodel.state.collectAsState()

    if(state.currentCategory == null)
        LaunchedEffect(Unit) {
            viewmodel.setCategory(category)
        }

    LaunchedEffect(state.currentCategory) {
        viewmodel.getProductsByCategory()
    }

    Scaffold(
        topBar = {
            SneakersTopBar(
                title = state.currentCategory?.value ?: "",
                navIcon = painterResource(R.drawable.back),
                onNavIconClick = navigateBack,
                hasActionIcon = false,
                backgroundIconColor = customBackgroundColor
            )
        }
    ) { contentPadding ->
            Column(
                modifier = Modifier
                    .background(customBackgroundColor)
                    .fillMaxSize()
                    .padding(contentPadding)
            ) {
                CategoriesRow(
                    categoriesList = state.categories,
                    onCategoryCardClick = { category ->
                        viewmodel.setCategory(category)
                    },
                    selectedCategory = state.currentCategory,
                    modifier = Modifier.padding(start = 20.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                if(state.products.isEmpty())
                    CustomLoadingIndicator()
                else
                    ProductsGrid(
                        productsList = state.products,
                        favoriteProductsIds = state.favoriteProductsIds,
                        cartProductsIds = state.cartProductsIds,
                        onCardClick = { product ->
                            navigateToDetails(product, state.products)
                        },
                        onFavoriteIconClick = { viewmodel.toggleFavorite(it) },
                        onButtonClick = { viewmodel.toggleCart(it) }
                    )
        }
    }
}