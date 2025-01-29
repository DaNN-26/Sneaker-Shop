package com.example.sneakershop.ui.main.popular

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.sneakershop.R
import com.example.sneakershop.navigation.NavigationGraph
import com.example.sneakershop.ui.components.topbar.SneakersTopBar
import com.example.sneakershop.ui.theme.customBackgroundColor

object PopularDestination : NavigationGraph {
    override val route = "popular"
}

@Composable
fun Popular() {
    Scaffold(
        topBar = {
            SneakersTopBar(
                title = "Популярное",
                navIcon = painterResource(R.drawable.back),
                actionsIcon = painterResource(R.drawable.favorite)
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(customBackgroundColor)
        ) {

        }
    }
}

@Composable
@Preview
fun PopularPreview() {
    Popular()
}