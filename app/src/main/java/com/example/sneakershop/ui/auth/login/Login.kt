package com.example.sneakershop.ui.auth.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sneakershop.ui.components.CustomButton
import com.example.sneakershop.ui.components.CustomTextField
import com.example.sneakershop.ui.theme.customAccentColor
import com.example.sneakershop.ui.theme.customBackgroundColor
import com.example.sneakershop.ui.theme.customSubTextDarkColor
import com.example.sneakershop.ui.theme.customTextColor

@Composable
fun Login() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(
                top = 121.dp,
                start = 20.dp,
                end = 20.dp
            )
        ) {
                Text(
                    text = "Привет!",
                    fontSize = 32.sp,
                    color = customTextColor
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Заполните свои данные или\nпродолжите через социальные медиа",
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    color = customSubTextDarkColor
                )
                Spacer(modifier = Modifier.height(35.dp))
                LoginInputForm(
                    onRecoverButtonClick = { /*TODO*/ }
                )
                Spacer(modifier = Modifier.height(10.dp))
                CustomButton(
                    onClick = { /*TODO*/ },
                    color = customAccentColor,
                    text = "Войти",
                    textColor = customBackgroundColor
                )
            }
        CreateAccountButton(
            onCreateAccountButtonClick = { /*TODO*/ }
        )
    }
}

@Composable
fun LoginInputForm(
    onRecoverButtonClick: () -> Unit
) {
    Column {
        Text(
            text = "Email",
            fontSize = 16.sp,
            color = customTextColor,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(12.dp))
        CustomTextField(
            value = "",
            onValueChange = {},
            placeholderText = "xyz@gmail.com"
        )
        Spacer(modifier = Modifier.height(18.dp))
        Text(
            text = "Пароль",
            fontSize = 16.sp,
            color = customTextColor,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(12.dp))
        CustomTextField(
            value = "",
            onValueChange = {},
            placeholderText = "••••••••",
            isPassword = true
        )
        TextButton(
            onClick = onRecoverButtonClick,
            modifier = Modifier
                .align(Alignment.End)
        ) {
            Text(
                text = "Восстановить",
                fontSize = 12.sp,
                color = customSubTextDarkColor
            )
        }
    }
}

@Composable
fun CreateAccountButton(
    onCreateAccountButtonClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier.padding(bottom = 50.dp)
    ) {
        TextButton(onClick = onCreateAccountButtonClick) {
            Text(
                text = "Вы впервые? ",
                fontSize = 16.sp,
                color = customSubTextDarkColor
            )
            Text(
                text = "Создать пользователя",
                fontSize = 16.sp,
                color = customTextColor
            )
        }
    }
}

@Composable
@Preview
fun LoginPreview() {
    Login()
}