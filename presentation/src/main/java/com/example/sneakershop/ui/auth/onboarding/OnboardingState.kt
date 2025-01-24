package com.example.sneakershop.ui.auth.onboarding

import com.example.sneakershop.R
import com.example.sneakershop.model.OnboardingSlide

data class OnboardingState(
    val slidesList: List<OnboardingSlide> = listOf(
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
    ),
    val currentSlide: OnboardingSlide = slidesList[0]
)
