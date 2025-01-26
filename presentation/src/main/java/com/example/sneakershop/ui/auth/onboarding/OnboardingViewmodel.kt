package com.example.sneakershop.ui.auth.onboarding

import androidx.lifecycle.ViewModel
import com.example.sneakershop.model.OnboardingSlide
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OnboardingViewmodel(
    private val navigateToHome: () -> Unit
) : ViewModel() {
    private val _state = MutableStateFlow(OnboardingState())
    val state = _state.asStateFlow()

    fun updateSlide() {
        if(state.value.currentSlide != state.value.slidesList.last())
            _state.update { it.copy(currentSlide = it.currentSlide.nextSlide()) }
        else
            navigateToHome()
    }

    private fun OnboardingSlide.nextSlide(): OnboardingSlide {
        val slideIndex = state.value.slidesList.indexOf(this)
        return state.value.slidesList[slideIndex + 1]
    }
}