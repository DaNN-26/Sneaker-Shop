package com.example.domain.model

data class Product(
    val id: Int,
    val title: String,
    val image: Int, //TODO При интеграции с БД переделать на String
    val price: Float,
    val isFavorite: Boolean,
    val isInShoppingCart: Boolean
)
