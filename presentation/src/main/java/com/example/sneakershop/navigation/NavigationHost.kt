package com.example.sneakershop.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sneakershop.ui.auth.login.Login
import com.example.sneakershop.ui.auth.login.LoginViewmodel
import com.example.sneakershop.ui.auth.onboarding.Onboarding
import com.example.sneakershop.ui.auth.onboarding.OnboardingViewmodel
import com.example.sneakershop.ui.main.home.Home

@Composable
fun NavigationHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            val viewmodel = LoginViewmodel(
                navigateToOnboarding = { navController.navigate("onboarding") }
            )
            Login(viewmodel = viewmodel)
        }
        composable("onboarding") {
            val viewmodel = OnboardingViewmodel(
                navigateToHome = { navController.navigate("home") }
            )
            Onboarding(viewmodel = viewmodel)

            BackHandler(true) {}

        }
        composable("home") {
            Home()

            BackHandler(true) {}
        }
    }
}