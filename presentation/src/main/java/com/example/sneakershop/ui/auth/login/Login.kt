package com.example.sneakershop.ui.auth.login

import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sneakershop.ui.components.CustomButton
import com.example.sneakershop.ui.components.CustomTextField
import com.example.sneakershop.ui.theme.customAccentColor
import com.example.sneakershop.ui.theme.customBackgroundColor
import com.example.sneakershop.ui.theme.customSubTextDarkColor
import com.example.sneakershop.ui.theme.customTextColor
import com.example.sneakershop.ui.theme.newPeninimMTFontFamily
import kotlinx.serialization.Serializable

@Composable
fun Login(
    viewmodel: LoginViewmodel,
    navigateToOnboarding: () -> Unit
) {
    val state by viewmodel.state.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(customBackgroundColor)
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(121.dp))
            Text(
                text = "Привет!",
                fontSize = 32.sp,
                fontFamily = newPeninimMTFontFamily,
                color = customTextColor
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Заполните свои данные или\nпродолжите через социальные медиа",
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontFamily = newPeninimMTFontFamily,
                color = customSubTextDarkColor
            )
            Spacer(modifier = Modifier.height(35.dp))
            LoginInputForm(
                email = state.email,
                onEmailChange = { viewmodel.updateEmail(it) },
                password = state.password,
                onPasswordChange = { viewmodel.updatePassword(it) },
                onRecoverButtonClick = { /*TODO*/ },
                isError = state.isError,
                emailErrorText = state.emailErrorText
            )
            Spacer(modifier = Modifier.height(10.dp))
            CustomButton(
                onClick = {
                    try {
                        viewmodel.authorize()
                        navigateToOnboarding()
                    } catch (e: Exception) {
                        Log.d("Login", e.message.toString())
                    }
                },
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
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    onRecoverButtonClick: () -> Unit,
    isError: Boolean,
    emailErrorText: String
) {
    Column {
        Text(
            text = "Email",
            fontSize = 16.sp,
            fontFamily = newPeninimMTFontFamily,
            color = customTextColor,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(12.dp))
        CustomTextField(
            value = email,
            onValueChange = { onEmailChange(it) },
            placeholderText = if(!isError) "xyz@gmail.com" else emailErrorText,
            isError = isError,
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        )
        Spacer(modifier = Modifier.height(18.dp))
        Text(
            text = "Пароль",
            fontSize = 16.sp,
            fontFamily = newPeninimMTFontFamily,
            color = customTextColor,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(12.dp))
        CustomTextField(
            value = password,
            onValueChange = { onPasswordChange(it) },
            placeholderText = "••••••••",
            isError = isError,
            isPassword = true,
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        )
        TextButton(
            onClick = onRecoverButtonClick,
            modifier = Modifier
                .align(Alignment.End)
        ) {
            Text(
                text = "Восстановить",
                fontSize = 12.sp,
                fontFamily = newPeninimMTFontFamily,
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
                fontFamily = newPeninimMTFontFamily,
                color = customSubTextDarkColor
            )
            Text(
                text = "Создать пользователя",
                fontSize = 16.sp,
                fontFamily = newPeninimMTFontFamily,
                color = customTextColor
            )
        }
    }
}