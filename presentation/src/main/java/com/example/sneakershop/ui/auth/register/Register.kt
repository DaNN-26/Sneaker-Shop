package com.example.sneakershop.ui.auth.register

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sneakershop.R
import com.example.sneakershop.ui.components.CustomButton
import com.example.sneakershop.ui.components.CustomDialogueBox
import com.example.sneakershop.ui.components.CustomTextField
import com.example.sneakershop.ui.components.topbar.SneakersTopBar
import com.example.sneakershop.ui.theme.customAccentColor
import com.example.sneakershop.ui.theme.customBackgroundColor
import com.example.sneakershop.ui.theme.customBlockColor
import com.example.sneakershop.ui.theme.customHintColor
import com.example.sneakershop.ui.theme.customSubTextDarkColor
import com.example.sneakershop.ui.theme.customTextColor
import com.example.sneakershop.ui.theme.newPeninimMTFontFamily
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun Register(
    viewmodel: RegisterViewmodel,
    navigateToHome: () -> Unit,
    navigateBack: () -> Unit
) {
    val state by viewmodel.state.collectAsState()
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            SneakersTopBar(
                title = "",
                navIcon = painterResource(id = R.drawable.back),
                onNavIconClick = navigateBack,
                backgroundIconColor = customBackgroundColor,
                hasActionIcon = false,
                containerColor = customBlockColor,
                modifier = Modifier.padding(top = 10.dp)
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { contentPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {

            if(state.isIncorrectEmail || state.isEmptyValues || state.isRegisteredEmail || state.isPasswordIsTooSmall)
                CustomDialogueBox(
                    onDismissRequest = viewmodel::dismissDialog,
                    title = if (state.isIncorrectEmail) "Некорректный email"
                    else if (state.isEmptyValues) "Пустые поля"
                    else if (state.isRegisteredEmail) "Аккаунт уже существует"
                    else "Пароль слишком короткий",
                    text = if (state.isIncorrectEmail) "Электронная почта должна соответствовать формату."
                    else if (state.isEmptyValues) "Вы оставили пустые поля, проверьте данные ещё раз."
                    else if (state.isRegisteredEmail) "Такая электронная почта уже зарегистрирована."
                    else "Минимальная длина пароля - 6 символов",
                    icon = if(state.isIncorrectEmail || state.isRegisteredEmail) R.drawable.email else 0,
                )

            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .blur(if (state.isIncorrectEmail || state.isEmptyValues || state.isRegisteredEmail || state.isPasswordIsTooSmall) 3.dp else 0.dp)
                    .background(customBlockColor)
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = "Регистрация",
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
                RegisterInputForm(
                    username = state.username,
                    onUsernameChange = { viewmodel.onUsernameChange(it) },
                    email = state.email,
                    onEmailChange = { viewmodel.onEmailChange(it) },
                    password = state.password,
                    onPasswordChange = { viewmodel.onPasswordChange(it) },
                    onPolicyCheckClick = { /*TODO*/ }
                )
                Spacer(modifier = Modifier.height(20.dp))
                CustomButton(
                    onClick = {
                        scope.launch(Dispatchers.Main) {
                            try {
                                viewmodel.createAccount()
                                navigateToHome()
                            } catch (e: Exception) {
                                Log.d("Register", e.message.toString())
                            }
                        }
                    },
                    color = customAccentColor,
                    text = "Зарегистрироваться",
                    textColor = customBackgroundColor
                )
            }
            ExistsAccountButton(
                onExistsAccountButtonClick = navigateBack
            )
        }
    }
}

@Composable
fun RegisterInputForm(
    username: String,
    onUsernameChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    onPolicyCheckClick: () -> Unit
) {
    Column {
        Text(
            text = "Ваше имя",
            fontSize = 16.sp,
            fontFamily = newPeninimMTFontFamily,
            color = customTextColor,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(12.dp))
        CustomTextField(
            value = username,
            onValueChange = { onUsernameChange(it) },
            placeholderText = "xxxxxxxx",
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        )
        Spacer(modifier = Modifier.height(12.dp))
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
            placeholderText = "xyz@gmail.com",
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        )
        Spacer(modifier = Modifier.height(12.dp))
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
            isPassword = true,
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        )
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(customBackgroundColor)
                    .align(Alignment.CenterVertically)
                    .size(18.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.policy_check),
                    contentDescription = null,
                    modifier = Modifier.size(10.dp)
                )
            }
            TextButton(
                onClick = onPolicyCheckClick
            ) {
                Text(
                    text = "Даю согласие на обработку\nперсональных данных",
                    fontSize = 16.sp,
                    fontFamily = newPeninimMTFontFamily,
                    color = customHintColor,
                    lineHeight = 16.38f.sp,
                    textDecoration = TextDecoration.Underline
                )
            }
        }
    }
}

@Composable
fun ExistsAccountButton(
    onExistsAccountButtonClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier.padding(bottom = 20.dp)
    ) {
        TextButton(onClick = onExistsAccountButtonClick) {
            Text(
                text = "Есть аккаунт? ",
                fontSize = 16.sp,
                fontFamily = newPeninimMTFontFamily,
                color = customSubTextDarkColor
            )
            Text(
                text = "Войти",
                fontSize = 16.sp,
                fontFamily = newPeninimMTFontFamily,
                color = customTextColor
            )
        }
    }
}