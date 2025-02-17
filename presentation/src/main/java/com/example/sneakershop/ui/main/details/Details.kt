package com.example.sneakershop.ui.main.details

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.domain.supabase.model.Product
import com.example.sneakershop.R
import com.example.sneakershop.ui.components.CustomButton
import com.example.sneakershop.ui.components.CustomLoadingIndicator
import com.example.sneakershop.ui.components.topbar.SneakersTopBar
import com.example.sneakershop.ui.theme.customAccentColor
import com.example.sneakershop.ui.theme.customBackgroundColor
import com.example.sneakershop.ui.theme.customBlockColor
import com.example.sneakershop.ui.theme.customHintColor
import com.example.sneakershop.ui.theme.customRedColor
import com.example.sneakershop.ui.theme.customSubTextLightColor
import com.example.sneakershop.ui.theme.customTextColor
import com.example.sneakershop.ui.theme.newPeninimMTFontFamily
import kotlinx.coroutines.launch

@Composable
fun Details(
    viewmodel: DetailsViewmodel,
    productId: Int,
    productsIdsList: List<Int>,
    navigateToCart: () -> Unit,
    navigateBack: () -> Unit
) {
    val state by viewmodel.state.collectAsState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewmodel.initializeProducts(productId, productsIdsList)
    }

    Scaffold(
        topBar = {
            SneakersTopBar(
                title = "Sneaker Shop",
                navIcon = painterResource(R.drawable.back),
                onNavIconClick = navigateBack,
                actionsIcon = painterResource(R.drawable.shopping_cart),
                onActionsIconClick = navigateToCart,
                isDetails = true
            )
        },
        bottomBar = {
            if(state.currentProduct != null)
                DetailsButtons(
                    onFavoriteClick = { viewmodel.toggleFavorite(state.currentProduct!!) },
                    onMainButtonClick = { viewmodel.toggleCart(state.currentProduct!!) },
                    isFavorite = state.favoriteProductsIds.contains(state.currentProduct!!.id),
                    isInCart = state.cartProductsIds.contains(state.currentProduct!!.id)
                )
        }
    ) { contentPadding ->
        if(state.currentProduct == null && state.products.isEmpty())
            CustomLoadingIndicator(
                modifier = Modifier.padding(contentPadding)
            )
        else {
            val pagerState = rememberPagerState(
                initialPage = state.products.indexOf(state.currentProduct)
            ) { state.products.size }

            key(pagerState.currentPage) {
                viewmodel.selectProduct(state.products[pagerState.currentPage])
            }

            DetailMainForm(
                product = state.currentProduct!!,
                productsList = state.products,
                onCardClick = { product ->
                    scope.launch {
                        pagerState.scrollToPage(state.products.indexOf(product))
                    }
                },
                pagerState = pagerState,
                modifier = Modifier
                    .background(customBackgroundColor)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(contentPadding)
                    .padding(horizontal = 12.dp)
            )
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun DetailMainForm(
    product: Product,
    productsList: List<Product>,
    onCardClick: (Product) -> Unit,
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.height(26.dp))
        DetailsTitle(
            title = product.title,
            gender = product.gender.value,
            price = String
                .format("%.2f", product.price)
                .replace(",", ".")
        )
        Spacer(modifier = Modifier.height(25.dp))
        DetailsImage(
            productsList = productsList,
            pagerState = pagerState
        )
        Spacer(modifier = Modifier.height(37.dp))
        DetailsSneakersRow(
            selectedId = product.id,
            items = productsList,
            onCardClick = { onCardClick(it) }
        )
        Spacer(modifier = Modifier.height(33.dp))
        DetailsDescription(
            description = product.description
        )
    }
}

@Composable
fun DetailsTitle(
    title: String,
    gender: String,
    price: String
) {
    Column(
        modifier = Modifier
            .padding(start = 8.dp, end = 120.dp)
    ) {
        Text(
            text = title,
            fontSize = 26.sp,
            fontFamily = newPeninimMTFontFamily,
            color = customTextColor,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(13.dp))
        Text(
            text = gender,
            fontSize = 16.sp,
            fontFamily = newPeninimMTFontFamily,
            color = customHintColor
        )
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = "₽$price",
            fontSize = 24.sp,
            fontFamily = newPeninimMTFontFamily,
            color = customTextColor
        )
    }
}

@Composable
fun DetailsImage(
    productsList: List<Product>,
    pagerState: PagerState
) {
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier.fillMaxWidth()
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
        ) { page ->
            AsyncImage(
                model = productsList[page].image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(start = 55.dp)
                    .size(250.dp, 125.dp)
            )
        }
        Image(
            painter = painterResource(R.drawable.slider),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 110.dp)
        )
    }
}

@Composable
fun DetailsSneakersRow(
    selectedId: Int,
    items: List<Product>,
    onCardClick: (Product) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp)
    ) {
        items(items) { product ->
            Card(
                onClick = { onCardClick(product) },
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = customBlockColor
                ),
                modifier = Modifier
                    .size(56.dp)
                    .border(
                        width = 3.dp,
                        color = if (selectedId == product.id) customAccentColor else Color.Transparent,
                        shape = RoundedCornerShape(18.dp)
                    )
            ) {
                AsyncImage(
                    model = product.image,
                    contentDescription = null,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp)
                )
            }
        }
    }
}

@Composable
fun DetailsDescription(
    description: String
) {
    var expanded by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        Text(
            text = description,
            fontSize = 14.sp,
            fontFamily = newPeninimMTFontFamily,
            color = customTextColor,
            maxLines = if(!expanded) 3 else Int.MAX_VALUE,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.animateContentSize(spring())
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = if(!expanded) "Подробнее" else "Свернуть",
            fontSize = 14.sp,
            fontFamily = newPeninimMTFontFamily,
            color = customAccentColor,
            modifier = Modifier
                .align(Alignment.End)
                .clip(RoundedCornerShape(6.dp))
                .clickable { expanded = !expanded }
        )
    }
}

@Composable
fun DetailsButtons(
    modifier: Modifier = Modifier,
    onFavoriteClick: () -> Unit,
    onMainButtonClick: () -> Unit,
    isFavorite: Boolean,
    isInCart: Boolean
) {
    val favoriteIcon = if (isFavorite) R.drawable.favorite_fill else R.drawable.favorite
    val favoriteIconTint = if (isFavorite) customRedColor else Color.Unspecified

    val buttonText = if (isInCart) "Добавлено" else "В корзину"

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 40.dp)
            .padding(horizontal = 20.dp)
    ) {
        IconButton(
            onClick = onFavoriteClick,
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = customSubTextLightColor
            ),
            modifier = Modifier.size(50.dp)
        ) {
            Icon(
                painter = painterResource(id = favoriteIcon),
                contentDescription = null,
                tint = favoriteIconTint
            )
        }
        Spacer(modifier = Modifier.width(18.dp))
        CustomButton(
            icon = R.drawable.bag,
            onClick = onMainButtonClick,
            color = customAccentColor,
            text = buttonText,
            textColor = customBlockColor
        )
    }
}