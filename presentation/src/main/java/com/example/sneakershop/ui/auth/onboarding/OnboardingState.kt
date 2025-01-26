package com.example.sneakershop.ui.auth.onboarding

import com.example.sneakershop.datasource.OnboardingSlidesDatasource
import com.example.sneakershop.model.OnboardingSlide

data class OnboardingState(
    val slidesList: List<OnboardingSlide> = OnboardingSlidesDatasource.list,
    val currentSlide: OnboardingSlide = OnboardingSlidesDatasource.list.first(),
)
