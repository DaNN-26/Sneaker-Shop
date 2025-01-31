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
import com.example.domain.model.ProductCategory
import com.example.sneakershop.R
import com.example.sneakershop.ui.components.main.CategoriesRow
import com.example.sneakershop.ui.components.main.ProductsGrid
import com.example.sneakershop.ui.components.topbar.SneakersTopBar
import com.example.sneakershop.ui.theme.customBackgroundColor

@Composable
fun Catalogue(
    viewmodel: CatalogueViewmodel,
    category: ProductCategory,
    navigateBack: () -> Unit
) {
    val state by viewmodel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewmodel.setCategory(category)
    }

    Scaffold(
        topBar = {
            SneakersTopBar(
                title = state.currentCategory?.value ?: "Категория не выбрана",
                navIcon = painterResource(R.drawable.back),
                actionsIcon = painterResource(R.drawable.favorite),
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
            ProductsGrid(
                productsList = state.products,
                onCardClick = { /*TODO*/ },
                onFavoriteIconClick = { /*TODO*/ },
                onButtonClick = { /*TODO*/ })
        }
    }
}