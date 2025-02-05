package com.example.sneakershop.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.sneakershop.ui.theme.customAccentColor
import com.example.sneakershop.ui.theme.customBlockColor
import com.example.sneakershop.ui.theme.customHintColor
import com.example.sneakershop.ui.theme.newPeninimMTFontFamily

@Composable
fun CustomDialogueBox(
    onDismissRequest: () -> Unit,
    title: String,
    text: String,
    icon: Int = 0
) {
    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clip(RoundedCornerShape(18.dp))
                .background(customBlockColor)
                .size(335.dp, 196.dp)
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            if(icon != 0)
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(customAccentColor)
                        .size(44.dp)
                ) {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = null,
                        tint = customBlockColor,
                        modifier = Modifier.size(24.dp)
                    )
                }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = title,
                fontSize = 16.sp,
                fontFamily = newPeninimMTFontFamily,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = text,
                fontSize = 16.sp,
                fontFamily = newPeninimMTFontFamily,
                color = customHintColor,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
            )
        }
    }
}