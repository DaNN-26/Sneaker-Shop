package com.example.sneakershop.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sneakershop.R
import com.example.sneakershop.ui.theme.customBackgroundColor

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String = "",
    isPassword: Boolean = false
) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        singleLine = true,
        placeholder = { Text(
            text = placeholderText,
            fontSize = 14.sp
        ) },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = customBackgroundColor,
            focusedContainerColor = customBackgroundColor,
            disabledContainerColor = customBackgroundColor,
            errorContainerColor = customBackgroundColor,
            unfocusedIndicatorColor = customBackgroundColor,
            errorIndicatorColor = customBackgroundColor,
            focusedIndicatorColor = customBackgroundColor,
            disabledIndicatorColor = customBackgroundColor
        ),
        modifier = Modifier
            .fillMaxWidth()
            .shadow(1.dp, RoundedCornerShape(16.dp)),

        visualTransformation = if(isPassword)
            PasswordVisualTransformation()
        else
            VisualTransformation.None,
        trailingIcon = {
            if(isPassword)
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(
                        painter = if(!isPasswordVisible)
                            painterResource(id = R.drawable.eye_close)
                        else
                            painterResource(id = R.drawable.eye_open),
                        contentDescription = "Hide password"
                    )
                }
        }
    )
}