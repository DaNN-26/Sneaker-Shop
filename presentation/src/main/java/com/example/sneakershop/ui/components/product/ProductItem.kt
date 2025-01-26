package com.example.sneakershop.ui.components.product

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.Product
import com.example.sneakershop.R
import com.example.sneakershop.ui.theme.customAccentColor
import com.example.sneakershop.ui.theme.customBackgroundColor
import com.example.sneakershop.ui.theme.customBlockColor
import com.example.sneakershop.ui.theme.customHintColor
import com.example.sneakershop.ui.theme.customRedColor
import com.example.sneakershop.ui.theme.customTextColor
import com.example.sneakershop.ui.theme.newPeninimMTFontFamily
import com.example.sneakershop.ui.theme.poppinsFontFamily
import com.example.sneakershop.ui.theme.ralewayFontFamily
import kotlin.math.max
import kotlin.math.roundToInt

@SuppressLint("DefaultLocale")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductItem(
    product: Product,
    onCardClick: () -> Unit,
    onFavoriteIconClick: () -> Unit,
    onButtonClick: () -> Unit
) {
    Card(
        onClick = onCardClick,
        colors = CardDefaults.cardColors(
            containerColor = customBlockColor
        ),
        modifier = Modifier.size(160.dp, 182.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 9.dp, top = 9.dp)
        ) {
            ProductImage(
                image = product.image,
                onFavoriteIconClick = onFavoriteIconClick,
                isFavorite = product.isFavorite
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "BEST SELLER",
                fontSize = 12.sp,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Medium,
                color = customAccentColor
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = product.title,
                fontSize = 16.sp,
                fontFamily = ralewayFontFamily,
                fontWeight = FontWeight.SemiBold,
                color = customHintColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(6.dp))
            ProductPrice(
                price = String
                    .format("%.2f", product.price)
                    .replace(",", "."),
                isInShoppingCart = product.isInShoppingCart,
                onButtonClick = onButtonClick
            )
        }
    }
}

@Composable
fun ProductImage(
    image: Int,
    isFavorite: Boolean,
    onFavoriteIconClick: () -> Unit
) {
    val favoriteIcon = if(isFavorite)
        R.drawable.favorite_fill
    else
        R.drawable.favorite

    val favoriteIconTint = if(isFavorite)
        customRedColor
    else
        customTextColor

    Box {
        Box(
            modifier = Modifier
                .size(28.dp)
                .clip(CircleShape)
                .background(customBackgroundColor)
        ) {
            IconButton(
                onClick = onFavoriteIconClick
            ) {
                Icon(
                    painter = painterResource(id = favoriteIcon),
                    contentDescription = null,
                    tint = favoriteIconTint,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(5.dp)
                )
            }
        }
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp)
                .size(117.dp, 70.dp)
        )
    }
}

@Composable
fun ProductPrice(
    price: String,
    isInShoppingCart: Boolean,
    onButtonClick: () -> Unit
) {
    val buttonIcon = if(isInShoppingCart)
        R.drawable.cart
    else
        R.drawable.add

    val buttonIconSize = if(isInShoppingCart)
        12.dp
    else
        24.dp

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "₽$price",
            fontSize = 14.sp,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Medium,
            color = customTextColor,
            modifier = Modifier.padding(top = 6.dp)
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 16.dp))
                .size(34.dp)
                .background(customAccentColor)
                .clickable { onButtonClick() }
        ) {
            Icon(
                painter = painterResource(id = buttonIcon),
                contentDescription = null,
                tint = customBlockColor,
                modifier = Modifier
                    .padding(start = 2.dp)
                    .size(buttonIconSize)
            )
        }
    }
}

@Composable
@Preview
fun ProductItemPreview() {
    ProductItem(
        product = Product(
            id = 1,
            title = "Nike Air Max",
            image = R.drawable.nike_test_image,
            isFavorite = false,
            price = 752.00f,
            isInShoppingCart = false
        ),
        onFavoriteIconClick = {},
        onCardClick = {},
        onButtonClick = {}
    )
}