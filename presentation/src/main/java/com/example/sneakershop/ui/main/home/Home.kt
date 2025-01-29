package com.example.sneakershop.ui.main.home

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.Product
import com.example.sneakershop.R
import com.example.sneakershop.datasource.ProductDatasource
import com.example.sneakershop.navigation.NavigationGraph
import com.example.sneakershop.ui.components.SearchField
import com.example.sneakershop.ui.components.navbar.SneakersNavBar
import com.example.sneakershop.ui.components.product.ProductItem
import com.example.sneakershop.ui.components.topbar.SneakersTopBar
import com.example.sneakershop.ui.theme.customAccentColor
import com.example.sneakershop.ui.theme.customBlockColor
import com.example.sneakershop.ui.theme.customTextColor
import com.example.sneakershop.ui.theme.newPeninimMTFontFamily
import com.example.sneakershop.ui.theme.ralewayFontFamily

object HomeDestination : NavigationGraph {
    override val route = "home"
}

@Composable
fun Home(
    viewmodel: HomeViewmodel
) {
    val state by viewmodel.state.collectAsState()

    Scaffold(
        topBar = {
            SneakersTopBar(
                title = "Главная",
                isHome = true,
                navIcon = painterResource(R.drawable.side_menu),
                actionsIcon = painterResource(R.drawable.shopping_cart)
            )
        },
        bottomBar = {
            SneakersNavBar()
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(top = 10.dp, start = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            HomeSearchBar(
                onFiltersButtonClick = { /*TODO*/ }
            )
            Spacer(modifier = Modifier.height(14.dp))
            HomeCategories(
                categoriesList = listOf("Все", "Outdoor", "Tennis", "Running"),
                onCategoryCardClick = { /*TODO*/ }
            )
            Spacer(modifier = Modifier.height(24.dp))
            HomePopularProducts(
                productsList = state.popularProducts,
                onAllButtonClick = { /*TODO*/ },
                onCardClick = { /*TODO*/ },
                onFavoriteIconClick = { /*TODO*/ },
                onButtonClick = { /*TODO*/ }
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
    onFiltersButtonClick: () -> Unit
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeCategories(
    categoriesList: List<String>,
    onCategoryCardClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Категории",
            fontSize = 16.sp,
            fontFamily = newPeninimMTFontFamily,
            color = customTextColor
        )
        Spacer(modifier = Modifier.height(19.dp))
        LazyRow {
            items(categoriesList) {
                Card(
                    onClick = onCategoryCardClick,
                    elevation = CardDefaults.cardElevation(1.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = customBlockColor
                    ),
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(108.dp, 40.dp)
                ) {
                    Text(
                        text = it,
                        fontSize = 12.sp,
                        fontFamily = ralewayFontFamily,
                        fontWeight = FontWeight.Medium,
                        color = customTextColor,
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize()
                    )
                }
            }
        }
    }
}

@Composable
fun HomePopularProducts(
    productsList: List<Product>,
    onAllButtonClick: () -> Unit,
    onCardClick: () -> Unit,
    onFavoriteIconClick: () -> Unit,
    onButtonClick: () -> Unit
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
                    onCardClick = onCardClick,
                    onFavoriteIconClick = onFavoriteIconClick,
                    onButtonClick = onButtonClick,
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

@Preview
@Composable
fun HomePreview() {
    Home(HomeViewmodel())
}