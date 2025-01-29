package com.example.sneakershop.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sneakershop.R
import com.example.sneakershop.ui.theme.customBackgroundColor
import com.example.sneakershop.ui.theme.customHintColor
import com.example.sneakershop.ui.theme.newPeninimMTFontFamily

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String = "",
    isError: Boolean = false,
    isPassword: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Unspecified,
    imeAction: ImeAction = ImeAction.Unspecified
) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    val passwordHideIcon = if(isPasswordVisible)
        R.drawable.eye_open
    else
        R.drawable.eye_close

    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        singleLine = true,
        placeholder = { Text(
            text = placeholderText,
            fontSize = 14.sp,
            fontFamily = newPeninimMTFontFamily,
            color = customHintColor
        ) },
        isError = isError,
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
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        modifier = Modifier
            .fillMaxWidth()
            .shadow(1.dp, RoundedCornerShape(16.dp)),

        visualTransformation = if(isPassword && !isPasswordVisible)
            PasswordVisualTransformation()
        else
            VisualTransformation.None,
        trailingIcon = {
            if(isPassword)
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(
                        painter = painterResource(id = passwordHideIcon),
                        contentDescription = "Hide password",
                        tint = customHintColor,
                        modifier = Modifier.size(18.dp)
                    )
                }
        }
    )
}