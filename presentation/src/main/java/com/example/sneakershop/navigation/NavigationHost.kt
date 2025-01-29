package com.example.sneakershop.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.sneakershop.ui.auth.login.Login
import com.example.sneakershop.ui.auth.login.LoginViewmodel
import com.example.sneakershop.ui.auth.onboarding.Onboarding
import com.example.sneakershop.ui.main.catalogue.Catalogue
import com.example.sneakershop.ui.main.catalogue.CatalogueViewmodel
import com.example.sneakershop.ui.main.home.Home
import com.example.sneakershop.ui.main.home.HomeViewmodel
import com.example.sneakershop.ui.main.popular.Popular
import com.example.sneakershop.ui.main.popular.PopularViewmodel

@Composable
fun NavigationHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavDestination.Home
    ) {
        composable<NavDestination.Login> {
            val viewmodel = LoginViewmodel()
            Login(
                viewmodel = viewmodel,
                navigateToOnboarding = {
                    navController.navigate(NavDestination.Onboarding) {
                        popUpTo(NavDestination.Login) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable<NavDestination.Onboarding> {
            Onboarding(
                navigateToHome = {
                    navController.navigate(NavDestination.Home) {
                        popUpTo(NavDestination.Onboarding) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable<NavDestination.Home> {
            val viewmodel = HomeViewmodel()
            Home(
                viewmodel = viewmodel,
                navigateToCatalogue = { navController.navigate(NavDestination.Catalogue(it)) },
                navigateToPopular = { navController.navigate(NavDestination.Popular) }
            )
        }
        composable<NavDestination.Popular> {
            val viewmodel = PopularViewmodel()
            Popular(
                viewmodel = viewmodel,
                navigateBack = { navController.popBackStack() }
            )
        }
        composable<NavDestination.Catalogue> {
            val catalogue: NavDestination.Catalogue = it.toRoute()
            val viewmodel = CatalogueViewmodel(category = catalogue.category)
            Catalogue(
                viewmodel = viewmodel,
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}