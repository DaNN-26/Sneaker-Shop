package com.example.sneakershop.datasource

import com.example.sneakershop.R
import com.example.sneakershop.model.OnboardingSlide

object OnboardingSlidesDatasource {
    val list = listOf(
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
}