package com.example.sneakershop.ui.components.topbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sneakershop.R
import com.example.sneakershop.ui.theme.customBackgroundColor
import com.example.sneakershop.ui.theme.customBlockColor
import com.example.sneakershop.ui.theme.newPeninimMTFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SneakersTopBar(
    title: String,
    isHome: Boolean = false,
    navIcon: Painter,
    actionsIcon: Painter,
    onNavIconClick: () -> Unit = {},
    onActionsIconClick: () -> Unit = {},
    hasActionIcon: Boolean = true,
    backgroundIconColor: Color = customBlockColor
) {
    CenterAlignedTopAppBar(
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
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = customBackgroundColor,
            scrolledContainerColor = customBackgroundColor
        ),
        navigationIcon = {
            SneakersTopBarIcon(
                onClick = onNavIconClick,
                backgroundIconColor = if (isHome) Color.Transparent else backgroundIconColor,
                icon = navIcon,
                modifier = Modifier
                    .padding(start = 20.dp)
                    .size(44.dp)
            )
        },
        actions = {
            if(hasActionIcon) {
                if (isHome)
                    Icon(
                        painter = actionsIcon,
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .padding(end = 20.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable { onActionsIconClick() }
                    )
                else
                    SneakersTopBarIcon(
                        onClick = onActionsIconClick,
                        backgroundIconColor = backgroundIconColor,
                        icon = actionsIcon,
                        modifier = Modifier
                            .padding(end = 20.dp)
                            .size(44.dp)
                    )
            }
        }
    )
}

@Composable
fun SneakersTopBarIcon(
    onClick: () -> Unit,
    backgroundIconColor: Color,
    icon: Painter,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = backgroundIconColor
        ),
        modifier = modifier
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = Color.Unspecified
        )
    }
}