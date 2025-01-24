package com.example.sneakershop.model

data class OnboardingSlide(
    val image: Int,
    val title: String,
    val description: String,
    val buttonText: String = "Далее"
)
