package com.example.sneakershop.ui.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.supabase.model.Product
import com.example.domain.supabase.model.ProductCategory
import com.example.sneakershop.R
import com.example.sneakershop.datasource.SneakersDatasource
import com.example.sneakershop.ui.components.SearchField
import com.example.sneakershop.ui.components.main.CategoriesRow
import com.example.sneakershop.ui.components.navbar.SneakersNavBar
import com.example.sneakershop.ui.components.product.ProductItem
import com.example.sneakershop.ui.components.topbar.SneakersTopBar
import com.example.sneakershop.ui.theme.customAccentColor
import com.example.sneakershop.ui.theme.customBackgroundColor
import com.example.sneakershop.ui.theme.customTextColor
import com.example.sneakershop.ui.theme.newPeninimMTFontFamily

@Composable
fun Home(
    viewmodel: HomeViewmodel,
    navigateToCatalogue: (ProductCategory) -> Unit,
    navigateToPopular: () -> Unit,
    navigateToDetails: (Product, List<Product>) -> Unit,
    navigateToSearch: () -> Unit,
    navigateToCart: () -> Unit,
    navigateToScreen: (Int) -> Unit
) {
    val state by viewmodel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewmodel.getFavoriteAndCartProductsIds()
    }

    Scaffold(
        topBar = {
            SneakersTopBar(
                title = "Главная",
                isHome = true,
                navIcon = painterResource(R.drawable.side_menu),
                actionsIcon = painterResource(R.drawable.shopping_cart),
                onActionsIconClick = navigateToCart
            )
        },
        bottomBar = {
            SneakersNavBar(
                navigateToScreen = { navigateToScreen(it) },
                navigateToCart = navigateToCart
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .background(customBackgroundColor)
                .fillMaxSize()
                .padding(top = contentPadding.calculateTopPadding())
                .padding(top = 5.dp, start = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            HomeSearchBar(
                onFiltersButtonClick = { /*TODO*/ },
                onSearchFieldClick = navigateToSearch
            )
            Spacer(modifier = Modifier.height(10.dp))
            CategoriesRow(
                categoriesList = SneakersDatasource.categories,
                onCategoryCardClick = { category ->
                    navigateToCatalogue(category)
                }
            )
            Spacer(modifier = Modifier.height(24.dp))
            HomePopularProducts(
                productsList = state.popularProducts,
                favoriteProductsIds = state.favoriteProductsIds,
                cartProductsIds = state.cartProductsIds,
                onAllButtonClick = navigateToPopular,
                onCardClick = { product ->
                    navigateToDetails(product, state.popularProducts)
                },
                onFavoriteIconClick = { viewmodel.toggleFavorite(it) },
                onButtonClick = { viewmodel.toggleCart(it) }
            )
            Spacer(modifier = Modifier.height(29.dp))
            HomeDiscounts(
                onAllButtonClick = { /*TODO*/ },
                onDiscountButtonClick = { /*TODO*/ }
            )
        }
    }
}

@Composable
fun HomeSearchBar(
    onFiltersButtonClick: () -> Unit,
    onSearchFieldClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        SearchField(
            value = "",
            onValueChange = {},
            enabled = false,
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
                .clickable { onSearchFieldClick() }
        )
        Icon(
            painter = painterResource(R.drawable.filters),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier
                .padding(top = 6.dp)
                .weight(0.5f)
                .clip(CircleShape)
                .clickable { onFiltersButtonClick() }
        )
    }
}

@Composable
fun HomePopularProducts(
    productsList: List<Product>,
    favoriteProductsIds: Set<Int>,
    cartProductsIds: Set<Int>,
    onAllButtonClick: () -> Unit,
    onCardClick: (Product) -> Unit,
    onFavoriteIconClick: (Product) -> Unit,
    onButtonClick: (Product) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Популярное",
                fontSize = 16.sp,
                fontFamily = newPeninimMTFontFamily,
                color = customTextColor
            )
            Text(
                text = "Все",
                fontSize = 12.sp,
                fontFamily = newPeninimMTFontFamily,
                color = customAccentColor,
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .clickable { onAllButtonClick() }
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            productsList.forEach { product ->
                if(product.isPopular)
                ProductItem(
                    product = product,
                    isFavorite = favoriteProductsIds.contains(product.id),
                    isInCart = cartProductsIds.contains(product.id),
                    onCardClick = { onCardClick(product) },
                    onFavoriteIconClick = { onFavoriteIconClick(product) },
                    onButtonClick = { onButtonClick(product) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun HomeDiscounts(
    onAllButtonClick: () -> Unit,
    onDiscountButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Акции",
                fontSize = 16.sp,
                fontFamily = newPeninimMTFontFamily,
                color = customTextColor
            )
            Text(
                text = "Все",
                fontSize = 12.sp,
                fontFamily = newPeninimMTFontFamily,
                color = customAccentColor,
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .clickable { onAllButtonClick() }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Image(
            painter = painterResource(R.drawable.discount_test_image),
            contentDescription = "discount",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .size(335.dp, 95.dp)
                .shadow(1.dp, RoundedCornerShape(16.dp), spotColor = Color.Gray)
                .clickable { onDiscountButtonClick() }
        )
    }
}