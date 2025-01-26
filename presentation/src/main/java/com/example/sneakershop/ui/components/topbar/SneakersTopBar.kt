package com.example.sneakershop.ui.components.topbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sneakershop.R
import com.example.sneakershop.ui.theme.newPeninimMTFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SneakersTopBar(
    title: String,
    isHome: Boolean = false,
    navIcon: Painter,
    actionsIcon: Painter,
    onNavIconClick: () -> Unit = {},
    onActionsIconClick: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                if(isHome)
                    Icon(
                        painter = painterResource(id = R.drawable.home_particle),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 132.dp, bottom = 28.dp)
                            .size(width = 18.dp, height = 19.dp)
                    )
                Text(
                    text = title,
                    fontSize = if (isHome) 32.sp else 16.sp,
                    fontFamily = newPeninimMTFontFamily
                )
            }
        },
        navigationIcon = {
            IconButton(
                onClick = onNavIconClick,
            ) {
                Icon(
                    painter = navIcon,
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
        },
        actions = {
            IconButton(
                onClick = onActionsIconClick,
                modifier = Modifier.size(44.dp)
            ) {
                Icon(
                    painter = actionsIcon,
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.fillMaxSize()
                )
            }
        },
        modifier = Modifier.padding(horizontal = 20.dp)
    )
}