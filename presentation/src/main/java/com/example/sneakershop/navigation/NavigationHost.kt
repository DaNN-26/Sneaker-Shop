package com.example.sneakershop.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sneakershop.ui.auth.login.Login
import com.example.sneakershop.ui.auth.login.LoginDestination
import com.example.sneakershop.ui.auth.login.LoginViewmodel
import com.example.sneakershop.ui.auth.onboarding.Onboarding
import com.example.sneakershop.ui.auth.onboarding.OnboardingDestination
import com.example.sneakershop.ui.main.home.Home
import com.example.sneakershop.ui.main.home.HomeDestination
import com.example.sneakershop.ui.main.home.HomeViewmodel
import com.example.sneakershop.ui.main.popular.Popular
import com.example.sneakershop.ui.main.popular.PopularDestination

@Composable
fun NavigationHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route
    ) {
        composable(LoginDestination.route) {
            val viewmodel = LoginViewmodel()
            Login(
                viewmodel = viewmodel,
                navigateToOnboarding = {
                    navController.navigate("onboarding") {
                        popUpTo("login") {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(OnboardingDestination.route) {
            Onboarding(
                navigateToHome = {
                    navController.navigate("home") {
                        popUpTo("onboarding") {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(HomeDestination.route) {
            val viewmodel = HomeViewmodel()
            Home(
                viewmodel = viewmodel
            )
        }
        composable(PopularDestination.route) {
            Popular()
        }
    }
}