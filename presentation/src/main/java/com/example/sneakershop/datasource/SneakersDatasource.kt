package com.example.sneakershop.datasource

import com.example.domain.model.Product
import com.example.domain.model.ProductCategory
import com.example.sneakershop.R
import com.example.sneakershop.model.OnboardingSlide

object SneakersDatasource {
    val onboardingSlides = listOf(
        OnboardingSlide(
            image = R.drawable.slide_1,
            title = "ДОБРО\nПОЖАЛОВАТЬ",
            description = "",
            buttonText = "Начать"
        ),
        OnboardingSlide(
            image = R.drawable.slide_2,
            title = "Начнем\nпутешествие",
            description = "Умная, великолепная и модная\nколлекция Изучите сейчас"
        ),
        OnboardingSlide(
            image = R.drawable.slide_3,
            title = "У Вас есть сила,\nчтобы",
            description = "В вашей комнате много красивых и привлекательных растений"
        )
    )
    val categories = listOf(
        ProductCategory.ALL,
        ProductCategory.OUTDOOR,
        ProductCategory.TENNIS,
        ProductCategory.RUNNING
    )
    val products = listOf(
        Product(
            id = 1,
            title = "Nike Air Max",
            image = R.drawable.nike_test_image,
            price = 752.00f,
            category = ProductCategory.TENNIS,
            isPopular = true,
            isFavorite = false,
            isInShoppingCart = false
        ),
        Product(
            id = 2,
            title = "Nike Air Max",
            image = R.drawable.nike_test_image,
            price = 752.00f,
            category = ProductCategory.TENNIS,
            isPopular = true,
            isFavorite = false,
            isInShoppingCart = true
        ),
        Product(
            id = 3,
            title = "Nike Air Max",
            image = R.drawable.nike_test_image,
            price = 752.00f,
            category = ProductCategory.OUTDOOR,
            isPopular = true,
            isFavorite = true,
            isInShoppingCart = false
        ),
        Product(
            id = 4,
            title = "Nike Air Max",
            image = R.drawable.nike_test_image,
            price = 752.00f,
            category = ProductCategory.RUNNING,
            isPopular = false,
            isFavorite = false,
            isInShoppingCart = true
        ),
        Product(
            id = 5,
            title = "Nike Air Max",
            image = R.drawable.nike_test_image,
            price = 752.00f,
            category = ProductCategory.RUNNING,
            isPopular = false,
            isFavorite = true,
            isInShoppingCart = false
        ),
        Product(
            id = 6,
            title = "Nike Air Max",
            image = R.drawable.nike_test_image,
            price = 752.00f,
            category = ProductCategory.TENNIS,
            isPopular = false,
            isFavorite = false,
            isInShoppingCart = false
        )
    )
}