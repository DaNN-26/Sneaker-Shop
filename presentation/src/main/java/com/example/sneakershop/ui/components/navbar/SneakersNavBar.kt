package com.example.sneakershop.ui.components.navbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sneakershop.R
import com.example.sneakershop.ui.theme.customAccentColor
import com.example.sneakershop.ui.theme.customBlockColor
import com.example.sneakershop.ui.theme.customSubTextDarkColor

@Composable
fun SneakersNavBar(
    initialIndexValue: Int = 0,
    navigateToScreen: (Int) -> Unit
) {
    val navBarItems = listOf(
        Pair(R.drawable.home, R.drawable.favorite),
        Pair(R.drawable.notification, R.drawable.profile)
    )

    val selectedIndex by remember { mutableIntStateOf(initialIndexValue) }

    NavigationBar(
        containerColor = Color.Transparent,
        modifier = Modifier
            .fillMaxWidth()
            .size(375.dp, 106.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(R.drawable.navbar_shape),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
            SneakersNavBarFloatingButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .shadow(16.dp, CircleShape, spotColor = customAccentColor)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(100.dp),
                modifier = Modifier
                    .padding(top = 30.dp)
            ) {
                navBarItems.forEachIndexed { pairIndex, pair ->
                    SneakersNavBarPair(
                        selectedIndex = selectedIndex,
                        iconPair = pair,
                        onClick = { newIndex ->
                            navigateToScreen(newIndex)
                        },
                        pairBaseIndex = pairIndex * 2,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
fun SneakersNavBarFloatingButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        onClick = onClick,
        shape = CircleShape,
        containerColor = customAccentColor,
        modifier = modifier
            .size(56.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.bag),
            contentDescription = null,
            tint = customBlockColor
        )
    }
}

@Composable
fun SneakersNavBarPair(
    selectedIndex: Int,
    iconPair: Pair<Int, Int>,
    onClick: (Int) -> Unit,
    pairBaseIndex: Int,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        iconPair.toList().forEachIndexed { index, item ->
            val globalIndex = pairBaseIndex + index
            NavigationBarItem(
                selected = selectedIndex == globalIndex,
                onClick = { onClick(globalIndex) },
                icon = {
                    Icon(
                        painter = painterResource(id = item),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = customAccentColor,
                    unselectedIconColor = customSubTextDarkColor,
                    indicatorColor = Color.White
                )
            )
        }
    }
}