package com.example.sneakershop.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.sneakershop.ui.auth.login.Login
import com.example.sneakershop.ui.auth.login.LoginViewmodel
import com.example.sneakershop.ui.auth.onboarding.Onboarding
import com.example.sneakershop.ui.auth.register.Register
import com.example.sneakershop.ui.auth.register.RegisterViewmodel
import com.example.sneakershop.ui.main.cart.Cart
import com.example.sneakershop.ui.main.cart.CartViewmodel
import com.example.sneakershop.ui.main.catalogue.Catalogue
import com.example.sneakershop.ui.main.catalogue.CatalogueViewmodel
import com.example.sneakershop.ui.main.details.Details
import com.example.sneakershop.ui.main.details.DetailsViewmodel
import com.example.sneakershop.ui.main.favorites.Favorites
import com.example.sneakershop.ui.main.favorites.FavoritesViewmodel
import com.example.sneakershop.ui.main.home.Home
import com.example.sneakershop.ui.main.home.HomeViewmodel
import com.example.sneakershop.ui.main.popular.Popular
import com.example.sneakershop.ui.main.popular.PopularViewmodel
import com.example.sneakershop.ui.main.search.Search
import com.example.sneakershop.ui.main.search.SearchViewmodel

@Composable
fun NavigationHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavDestination.Onboarding
    ) {
        composable<NavDestination.Onboarding> {
            Onboarding(
                navigateToLogin = {
                    navController.navigate(NavDestination.Login) {
                        popUpTo<NavDestination.Onboarding> {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable<NavDestination.Login> {
            val viewmodel = hiltViewModel<LoginViewmodel>()
            Login(
                viewmodel = viewmodel,
                navigateToHome = {
                    navController.navigate(NavDestination.Home) {
                        popUpTo<NavDestination.Login> {
                            inclusive = true
                        }
                    }
                },
                navigateToRegister = { navController.navigate(NavDestination.Register) }
            )
        }
        composable<NavDestination.Register> {
            val viewmodel = hiltViewModel<RegisterViewmodel>()
            Register(
                viewmodel = viewmodel,
                navigateToHome = {
                    navController.navigate(NavDestination.Home) {
                        popUpTo<NavDestination.Register> {
                            inclusive = true
                        }
                    }
                },
                navigateBack = { navController.popBackStack() }
            )
        }
        composable<NavDestination.Home> {
            val viewmodel = hiltViewModel<HomeViewmodel>()
            Home(
                viewmodel = viewmodel,
                navigateToCatalogue = { category ->
                    navController.navigate(NavDestination.Catalogue(category))
                },
                navigateToPopular = { navController.navigate(NavDestination.Popular) },
                navigateToDetails = { product, productsList ->
                    navController.navigate(NavDestination.Details(
                        productId = product.id,
                        productsIdsList = productsList.map { it.id }
                    ))
                },
                navigateToSearch = { navController.navigate(NavDestination.Search) },
                navigateToCart = { navController.navigate(NavDestination.Cart) },
                navigateToScreen = { index ->
                    when (index) {
                        0 -> navController.navigate(NavDestination.Home)
                        1 -> navController.navigate(NavDestination.Favorites)
                        2 -> { /*TODO*/ }
                        3 -> { /*TODO*/ }
                    }
                }
            )
        }
        composable<NavDestination.Popular> {
            val viewmodel = hiltViewModel<PopularViewmodel>()
            Popular(
                viewmodel = viewmodel,
                navigateToDetails = { product, productsList ->
                    navController.navigate(NavDestination.Details(
                        productId = product.id,
                        productsIdsList = productsList.map { it.id }
                    ))
                },
                navigateBack = { navController.popBackStack() }
            )
        }
        composable<NavDestination.Catalogue> { navBackStackEntry ->
            val catalogue: NavDestination.Catalogue = navBackStackEntry.toRoute()
            val viewmodel = hiltViewModel<CatalogueViewmodel>()
            Catalogue(
                viewmodel = viewmodel,
                category = catalogue.category,
                navigateToDetails = { product, productsList ->
                    navController.navigate(NavDestination.Details(
                        productId = product.id,
                        productsIdsList = productsList.map { it.id }
                    ))
                },
                navigateBack = { navController.popBackStack() }
            )
        }
        composable<NavDestination.Details> { navBackStackEntry ->
            val details: NavDestination.Details = navBackStackEntry.toRoute()
            val viewmodel = hiltViewModel<DetailsViewmodel>()
            Details(
                viewmodel = viewmodel,
                productId = details.productId,
                productsIdsList = details.productsIdsList,
                navigateToCart = { navController.navigate(NavDestination.Cart) },
                navigateBack = { navController.popBackStack() }
            )
        }
        composable<NavDestination.Favorites> {
            val viewmodel = hiltViewModel<FavoritesViewmodel>()
            Favorites(
                viewmodel = viewmodel,
                navigateToDetails = { product, productsList ->
                    navController.navigate(NavDestination.Details(
                        productId = product.id,
                        productsIdsList = productsList.map { it.id }
                    ))
                },
                navigateBack = { navController.popBackStack() },
                navigateToCart = { navController.navigate(NavDestination.Cart) },
                navigateToScreen = { index ->
                    when (index) {
                        0 -> navController.navigate(NavDestination.Home)
                        1 -> navController.navigate(NavDestination.Favorites)
                        2 -> { /*TODO*/ }
                        3 -> { /*TODO*/ }
                    }
                }
            )
        }
        composable<NavDestination.Search> {
            val viewmodel = hiltViewModel<SearchViewmodel>()
            Search(
                viewmodel = viewmodel,
                navigateToDetails = { product, productsList ->
                    navController.navigate(NavDestination.Details(
                        productId = product.id,
                        productsIdsList = productsList.map { it.id }
                    ))
                },
                navigateBack = { navController.popBackStack() }
            )
        }
        composable<NavDestination.Cart> {
            val viewmodel = hiltViewModel<CartViewmodel>()
            Cart(
                viewmodel = viewmodel,
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}