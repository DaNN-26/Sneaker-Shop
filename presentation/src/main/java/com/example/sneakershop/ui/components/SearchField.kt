package com.example.sneakershop.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sneakershop.R
import com.example.sneakershop.ui.theme.customBlockColor
import com.example.sneakershop.ui.theme.customHintColor
import com.example.sneakershop.ui.theme.newPeninimMTFontFamily

@Composable
fun SearchField(
    value: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        singleLine = true,
        enabled = enabled,
        placeholder = {
            Text(
                text = "Поиск",
                fontSize = 12.sp,
                fontFamily = newPeninimMTFontFamily,
                color = customHintColor,
                modifier = Modifier.padding(start = 8.dp)
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = customBlockColor,
            focusedContainerColor = customBlockColor,
            disabledContainerColor = customBlockColor,
            errorContainerColor = customBlockColor,
            unfocusedIndicatorColor = customBlockColor,
            errorIndicatorColor = customBlockColor,
            focusedIndicatorColor = customBlockColor,
            disabledIndicatorColor = customBlockColor
        ),
        modifier = modifier
            .shadow(2.dp, RoundedCornerShape(16.dp), spotColor = Color.Gray),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.search),
                contentDescription = "Search",
                tint = customHintColor,
                modifier = Modifier.padding(start = 26.dp)
            )
        }
    )
}