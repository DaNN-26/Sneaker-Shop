package com.example.sneakershop.ui.main.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sneakershop.R
import com.example.sneakershop.ui.components.CustomTextField
import com.example.sneakershop.ui.components.SearchField
import com.example.sneakershop.ui.components.topbar.SneakersTopBar
import com.example.sneakershop.ui.theme.customBackgroundColor
import com.example.sneakershop.ui.theme.customBlockColor
import com.example.sneakershop.ui.theme.customTextColor
import com.example.sneakershop.ui.theme.newPeninimMTFontFamily
import com.example.sneakershop.ui.theme.poppinsFontFamily
import com.example.sneakershop.ui.theme.ralewayFontFamily

@Composable
fun Home() {
    Scaffold(
        topBar = {
            SneakersTopBar(
                title = "Главная",
                isHome = true,
                navIcon = painterResource(R.drawable.side_menu),
                actionsIcon = painterResource(R.drawable.shopping_cart)
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(top = 18.dp, start = 20.dp)
        ) {
            HomeSearchBar(
                onFiltersButtonClick = {}
            )
            Spacer(modifier = Modifier.height(22.dp))
            HomeCategories(categoriesList = listOf("Все", "Outdoor", "Tennis", "Adidas"))
        }
    }
}

@Composable
fun HomeSearchBar(
    onFiltersButtonClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        SearchField(
            value = "",
            onValueChange = {},
            enabled = false
        )
        Spacer(modifier = Modifier.width(10.dp))
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(65.dp)
                .clip(CircleShape)
                .clickable { onFiltersButtonClick() }
        ) {
            Icon(
                painter = painterResource(R.drawable.filters),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 6.dp)
            )
        }
    }
}

@Composable
fun HomeCategories(
    categoriesList: List<String>
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Категории",
            fontSize = 16.sp,
            fontFamily = newPeninimMTFontFamily,
            color = customTextColor,
            modifier = Modifier.padding(start = 1.dp)
        )
        Spacer(modifier = Modifier.height(19.dp))
        LazyRow {
            items(categoriesList) {
                Card(
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

@Preview
@Composable
fun HomePreview() {
    Home()
}