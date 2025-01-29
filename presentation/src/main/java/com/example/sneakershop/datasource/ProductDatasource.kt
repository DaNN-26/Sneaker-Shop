package com.example.sneakershop.datasource

import com.example.domain.model.Product
import com.example.domain.model.ProductCategory
import com.example.sneakershop.R

object ProductDatasource {
    val list = listOf(
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
            isFavorite = false,
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
            isFavorite = false,
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
            isInShoppingCart = true
        )
    )
}