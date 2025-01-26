package com.example.sneakershop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.sneakershop.navigation.NavigationHost
import com.example.sneakershop.ui.auth.login.Login
import com.example.sneakershop.ui.auth.login.LoginViewmodel
import com.example.sneakershop.ui.auth.onboarding.Onboarding
import com.example.sneakershop.ui.auth.onboarding.OnboardingViewmodel
import com.example.sneakershop.ui.main.home.Home
import com.example.sneakershop.ui.theme.SneakerShopTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SneakerShopTheme {
                NavigationHost()
            }
        }
    }
}