package com.example.sneakershop.ui.components.product

import android.annotation.SuppressLint
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.domain.model.Product
import com.example.sneakershop.R
import com.example.sneakershop.ui.theme.customAccentColor
import com.example.sneakershop.ui.theme.customBackgroundColor
import com.example.sneakershop.ui.theme.customBlockColor
import com.example.sneakershop.ui.theme.customHintColor
import com.example.sneakershop.ui.theme.customTextColor
import com.example.sneakershop.ui.theme.poppinsFontFamily
import com.example.sneakershop.ui.theme.ralewayFontFamily

@SuppressLint("DefaultLocale")
@Composable
fun ProductItem(
    product: Product,
    onCardClick: (Product) -> Unit,
    onFavoriteIconClick: () -> Unit,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = { onCardClick(product) },
        colors = CardDefaults.cardColors(
            containerColor = customBlockColor
        ),
        modifier = modifier.size(160.dp, 182.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 9.dp, top = 9.dp)
        ) {
            ProductImage(
                image = product.image,
                onFavoriteIconClick = onFavoriteIconClick,
                //isFavorite = product.isFavorite
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "BEST SELLER",
                fontSize = 12.sp,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Medium,
                color = customAccentColor
            )
            Text(
                text = product.title,
                fontSize = 16.sp,
                fontFamily = ralewayFontFamily,
                fontWeight = FontWeight.SemiBold,
                color = customHintColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            ProductPrice(
                price = String
                    .format("%.2f", product.price)
                    .replace(",", "."),
                //isInShoppingCart = product.isInShoppingCart,
                onButtonClick = onButtonClick
            )
        }
    }
}

@Composable
fun ProductImage(
    image: String,
    //isFavorite: Boolean,
    onFavoriteIconClick: () -> Unit
) {
//    val favoriteIcon = if(isFavorite)
//        R.drawable.favorite_fill
//    else
//        R.drawable.favorite
//
//    val favoriteIconTint = if(isFavorite)
//        customRedColor
//    else
//        customTextColor

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
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
                    painter = painterResource(id = R.drawable.favorite),
                    contentDescription = null,
                    tint = customTextColor,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(5.dp)
                )
            }
        }
        AsyncImage(
            model = image,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 6.dp)
                .size(117.dp, 70.dp)
        )
    }
}

@Composable
fun ProductPrice(
    price: String,
    //isInShoppingCart: Boolean,
    onButtonClick: () -> Unit
) {
//    val buttonIcon = if(isInShoppingCart)
//        R.drawable.cart
//    else
//        R.drawable.add
//
//    val buttonIconSize = if(isInShoppingCart)
//        12.dp
//    else
//        24.dp

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
                painter = painterResource(id = R.drawable.add),
                contentDescription = null,
                tint = customBlockColor,
                modifier = Modifier
                    .padding(start = 2.dp)
                    .size(24.dp)
            )
        }
    }
}