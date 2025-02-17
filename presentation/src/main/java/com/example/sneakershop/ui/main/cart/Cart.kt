package com.example.sneakershop.ui.main.cart

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.domain.supabase.model.Product
import com.example.sneakershop.R
import com.example.sneakershop.ui.components.CustomButton
import com.example.sneakershop.ui.components.topbar.SneakersTopBar
import com.example.sneakershop.ui.theme.customAccentColor
import com.example.sneakershop.ui.theme.customBackgroundColor
import com.example.sneakershop.ui.theme.customBlockColor
import com.example.sneakershop.ui.theme.customRedColor
import com.example.sneakershop.ui.theme.customSubTextDarkColor
import com.example.sneakershop.ui.theme.customTextColor
import com.example.sneakershop.ui.theme.newPeninimMTFontFamily

enum class Anchors {
    Left, Center, Right
}

@SuppressLint("DefaultLocale")
@Composable
fun Cart(
    viewmodel: CartViewmodel,
    navigateBack: () -> Unit
) {
    val state by viewmodel.state.collectAsState()

    key(state.productPairs) {
        viewmodel.calculatePrices()
    }

    Scaffold(
        topBar = {
            SneakersTopBar(
                title = "Корзина",
                navIcon = painterResource(R.drawable.back),
                onNavIconClick = navigateBack,
                hasActionIcon = false
            )
        },
        bottomBar = {
            CartMakingOrder(
                onMakingOrderClick = { /*TODO*/ },
                productsPrice = String
                    .format("%.2f", state.productsPrice)
                    .replace(",", "."),
                deliveryPrice = String
                    .format("%.2f", state.deliveryPrice)
                    .replace(",", "."),
                totalPrice = String
                    .format("%.2f", state.totalPrice)
                    .replace(",", ".")
            )
        }
    ) { contentPadding ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(14.dp),
            modifier = Modifier
                .background(customBackgroundColor)
                .fillMaxSize()
                .padding(contentPadding)
                .padding(horizontal = 20.dp)
        ) {
            item {
                Text(
                    text = "${state.productPairs.size} товара",
                    fontSize = 16.sp,
                    color = customTextColor,
                    fontFamily = newPeninimMTFontFamily
                )
            }
            items(state.productPairs) { pair ->
                CartAnimatedCard(
                    product = pair.first,
                    quantity = pair.second,
                    onPlusClick = { viewmodel.addToCart(pair) },
                    onMinusClick = { viewmodel.removeFromCart(pair) },
                    onDeleteClick = { viewmodel.deleteFromCart(pair) }
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CartAnimatedCard(
    product: Product,
    quantity: Int,
    onPlusClick: () -> Unit,
    onMinusClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    val decayAnimationSpec = rememberSplineBasedDecay<Float>()

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val cardMinWidth = screenWidth * 0.7f
    val buttonWidth = screenWidth * 0.3f

    val anchors = DraggableAnchors {
        Anchors.Left at -buttonWidth.value
        Anchors.Center at 0f
        Anchors.Right at buttonWidth.value
    }
    val anchoredDraggableState = remember {
        AnchoredDraggableState(
            anchors = anchors,
            initialValue = Anchors.Center,
            positionalThreshold = { distance: Float -> distance / 2 },
            velocityThreshold = { 1000f },
            snapAnimationSpec = tween(300),
            decayAnimationSpec = decayAnimationSpec
        )
    }
    val cardWidth by animateDpAsState(
        targetValue = if(anchoredDraggableState.currentValue == Anchors.Center)
            screenWidth
        else
            cardMinWidth,
        animationSpec = tween(500),
        label = "",
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .anchoredDraggable(
                state = anchoredDraggableState,
                orientation = Orientation.Horizontal
            )
    ) {
        if (anchoredDraggableState.currentValue == Anchors.Right)
            CartActionButton(
                onPlusClick = onPlusClick,
                onMinusClick = onMinusClick,
                amount = quantity,
                anchor = Anchors.Right,
                modifier = Modifier
                    .weight(0.22f)
                    .padding(end = 10.dp)
            )
        CartItemCard(
            product = product,
            modifier = Modifier
                .width(cardWidth)
        )
        if (anchoredDraggableState.currentValue == Anchors.Left)
            CartActionButton(
                onDeleteClick = onDeleteClick,
                anchor = Anchors.Left,
                modifier = Modifier
                    .weight(0.22f)
                    .padding(start = 10.dp)
            )
    }
}

@Composable
fun CartActionButton(
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit = {},
    onPlusClick: () -> Unit = {},
    onMinusClick: () -> Unit = {},
    amount: Int = 0,
    anchor: Anchors
) {
    val color = when(anchor) {
        Anchors.Right -> customAccentColor
        else -> customRedColor
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(color)
            .height(104.dp)
    ) {
        if(anchor == Anchors.Left)
            IconButton(onClick = onDeleteClick) {
                Icon(
                    painter = painterResource(id = R.drawable.trash),
                    contentDescription = null,
                    tint = customBlockColor,
                    modifier = Modifier.size(26.dp)
                )
            }
        if(anchor == Anchors.Right)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(onClick = onPlusClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.add),
                        contentDescription = null,
                        tint = customBlockColor
                    )
                }
                Text(
                    text = amount.toString(),
                    fontSize = 14.sp,
                    color = customBlockColor,
                    fontStyle = FontStyle.Italic,
                    fontFamily = newPeninimMTFontFamily
                )
                IconButton(onClick = onMinusClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.minus),
                        contentDescription = null,
                        tint = customBlockColor
                    )
                }
            }
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun CartItemCard(
    modifier: Modifier = Modifier,
    product: Product
) {
    Card(
        elevation = CardDefaults.cardElevation(0.2f.dp),
        colors = CardDefaults.cardColors(
            containerColor = customBlockColor
        ),
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 10.dp,
                    start = 10.dp,
                    bottom = 9.dp
                )
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(customBackgroundColor)
                    .size(87.dp, 85.dp)
            ) {
                AsyncImage(
                    model = product.image,
                    contentDescription = null,
                    modifier = Modifier.padding(5.dp)
                )
            }
            Spacer(modifier = Modifier.width(30.dp))
            Column {
                Spacer(modifier = Modifier.height(19.dp))
                Text(
                    text = product.title,
                    fontSize = 16.sp,
                    color = customTextColor,
                    fontFamily = newPeninimMTFontFamily,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "₽" + String.format("%.2f", product.price)
                        .replace(",", "."),
                    fontSize = 14.sp,
                    color = customTextColor,
                    fontFamily = newPeninimMTFontFamily
                )
            }
        }
    }
}

@Composable
fun CartMakingOrder(
    onMakingOrderClick: () -> Unit,
    productsPrice: String,
    deliveryPrice: String,
    totalPrice: String
) {
    Column(
        modifier = Modifier
            .background(customBlockColor)
            .fillMaxWidth()
            .height(258.dp)
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(34.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Сумма",
                fontSize = 16.sp,
                color = customSubTextDarkColor,
                fontFamily = newPeninimMTFontFamily
            )
            Text(
                text = "₽$productsPrice",
                fontSize = 16.sp,
                color = customTextColor,
                fontFamily = newPeninimMTFontFamily
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Доставка",
                fontSize = 16.sp,
                color = customSubTextDarkColor,
                fontFamily = newPeninimMTFontFamily
            )
            Text(
                text = "₽$deliveryPrice",
                fontSize = 16.sp,
                color = customTextColor,
                fontFamily = newPeninimMTFontFamily
            )
        }
        Spacer(modifier = Modifier.height(18.dp))
        CartDashDivider(
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Итого",
                fontSize = 16.sp,
                color = customTextColor,
                fontFamily = newPeninimMTFontFamily
            )
            Text(
                text = "₽$totalPrice",
                fontSize = 16.sp,
                color = customAccentColor,
                fontFamily = newPeninimMTFontFamily
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        CustomButton(
            onClick = onMakingOrderClick,
            color = customAccentColor,
            text = "Оформить заказ",
            textColor = customBlockColor
        )
    }
}

@Composable
fun CartDashDivider(
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        drawRoundRect(
            color = customSubTextDarkColor,
            style = Stroke(
                width = 2.dp.toPx(),
                pathEffect = PathEffect.dashPathEffect(
                    intervals = floatArrayOf(10f, 20f),
                    phase = 22f
                )
            )
        )
    }
}