package com.example.sneakershop.ui.main.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.localdb.model.SearchQuery
import com.example.domain.supabase.model.Product
import com.example.sneakershop.R
import com.example.sneakershop.ui.components.CustomLoadingIndicator
import com.example.sneakershop.ui.components.SearchField
import com.example.sneakershop.ui.components.main.ProductsGrid
import com.example.sneakershop.ui.components.topbar.SneakersTopBar
import com.example.sneakershop.ui.theme.customSubTextDarkColor
import com.example.sneakershop.ui.theme.newPeninimMTFontFamily

@Composable
fun Search(
    viewmodel: SearchViewmodel,
    navigateToDetails: (Product, List<Product>) -> Unit,
    navigateBack: () -> Unit
) {
    val state by viewmodel.state.collectAsState()

    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()

        viewmodel.getSearchQueries()

        if(state.products.isNotEmpty())
            viewmodel.getFavoriteAndCartProductsIds()
    }

    Scaffold(
        topBar = {
            SneakersTopBar(
                title = "Поиск",
                navIcon = painterResource(id = R.drawable.back),
                hasActionIcon = false,
                onNavIconClick = navigateBack
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            Spacer(modifier = Modifier.height(15.dp))
            SearchField(
                onDoneClick = { viewmodel.getProductsBySearch(state.query) },
                value = state.query,
                isError = state.isEmptyQuery,
                onValueChange = { viewmodel.onQueryChange(it) },
                hasMicrophoneIcon = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester)
                    .padding(horizontal = 20.dp)
            )
            Spacer(modifier = Modifier.height(28.dp))
            if(state.isLoading)
                CustomLoadingIndicator()
            else
                if(state.products.isEmpty())
                    SearchQueries(
                        onSearchQueryClick = { newQuery ->
                            viewmodel.onQueryChange(newQuery)
                            viewmodel.getProductsBySearch(newQuery)
                        },
                        onSearchQueryLongClick = { searchQuery ->
                            viewmodel.deleteSearchQuery(searchQuery)
                        },
                        queries = state.queriesList
                    )
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchQueries(
    onSearchQueryClick: (String) -> Unit,
    onSearchQueryLongClick: (SearchQuery) -> Unit,
    queries: List<SearchQuery>,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        items(queries) { searchQuery ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .combinedClickable(
                        onClick = { onSearchQueryClick(searchQuery.query) },
                        onLongClick = { onSearchQueryLongClick(searchQuery) }
                    )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.clock),
                    contentDescription = null,
                    tint = customSubTextDarkColor
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = searchQuery.query,
                    fontSize = 14.sp,
                    fontFamily = newPeninimMTFontFamily
                )
            }
        }
    }
}