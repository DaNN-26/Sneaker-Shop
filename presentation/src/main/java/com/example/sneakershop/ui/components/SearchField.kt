package com.example.sneakershop.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sneakershop.R
import com.example.sneakershop.ui.theme.customBlockColor
import com.example.sneakershop.ui.theme.customHintColor
import com.example.sneakershop.ui.theme.customRedColor
import com.example.sneakershop.ui.theme.customSubTextDarkColor
import com.example.sneakershop.ui.theme.newPeninimMTFontFamily

@Composable
fun SearchField(
    onDoneClick: () -> Unit = {},
    value: String,
    isError: Boolean = false,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    hasMicrophoneIcon: Boolean = false,
    @SuppressLint("ModifierParameter")
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        singleLine = true,
        enabled = enabled,
        isError = isError,
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
            errorIndicatorColor = customRedColor,
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
        },
        trailingIcon = {
            if(hasMicrophoneIcon)
                Row(
                    horizontalArrangement = Arrangement.End
                ) {
                    VerticalDivider(
                        color = customSubTextDarkColor,
                        modifier = Modifier
                            .size(24.dp)
                            .wrapContentWidth(Alignment.End)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.microphone),
                        contentDescription = null,
                        tint = customSubTextDarkColor,
                        modifier = Modifier
                            .padding(end = 14.dp)
                            .size(26.dp, 24.dp)
                    )
                }
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { onDoneClick() },
        )
    )
}