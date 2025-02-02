package com.example.sneakershop.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sneakershop.ui.theme.newPeninimMTFontFamily

@Composable
fun CustomButton(
    onClick: () -> Unit,
    icon: Int = 0,
    color: Color,
    text: String,
    textColor: Color,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = color
        ),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        if (icon != 0)
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.weight(0.1f)
            )
        Text(
            text = text,
            fontSize = 14.sp,
            fontFamily = newPeninimMTFontFamily,
            fontWeight = FontWeight.Light,
            color = textColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
    }
}