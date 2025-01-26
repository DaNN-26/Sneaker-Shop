package com.example.sneakershop.ui.auth.onboarding

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sneakershop.R
import com.example.sneakershop.model.OnboardingSlide
import com.example.sneakershop.ui.components.CustomButton
import com.example.sneakershop.ui.theme.customBackgroundColor
import com.example.sneakershop.ui.theme.customBlockColor
import com.example.sneakershop.ui.theme.customDisableColor
import com.example.sneakershop.ui.theme.customSubTextLightColor
import com.example.sneakershop.ui.theme.customTextColor
import com.example.sneakershop.ui.theme.newPeninimMTFontFamily

@Composable
fun Onboarding(
    viewmodel: OnboardingViewmodel
) {
    val state by viewmodel.state.collectAsState()

    val gradient = Brush.linearGradient(colors = listOf(
        Color(72, 178, 231),
        Color(68, 169, 220),
        Color(43, 107, 139)
    ))

        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(gradient)
                    .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(70.dp))
                if (state.currentSlide == state.slidesList[0]) {
                    Text(
                        text = state.currentSlide.title,
                        fontSize = 30.sp,
                        fontFamily = newPeninimMTFontFamily,
                        color = customBlockColor,
                        textAlign = TextAlign.Center,
                        lineHeight = 32.sp,
                        modifier = Modifier.weight(0.5f)
                    )
                    Spacer(modifier = Modifier.height(120.dp))
                }
                OnboardingElement(
                    slide = state.currentSlide,
                    slidesList = state.slidesList,
                    modifier = Modifier.weight(3f)
                )
                CustomButton(
                    onClick = viewmodel::updateSlide,
                    color = customBlockColor,
                    text = state.currentSlide.buttonText,
                    textColor = customTextColor,
                    modifier = Modifier
                        .weight(
                            if (state.currentSlide == state.slidesList[0])
                                0.36f
                            else
                                0.25f
                        )
                        .padding(horizontal = 20.dp)
                )
                Spacer(modifier = Modifier.height(35.dp))
            }
    }
}

@Composable
fun OnboardingElement(
    slide: OnboardingSlide,
    slidesList: List<OnboardingSlide>,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = slide.image),
            contentDescription = null,
            modifier = Modifier.size(width = 375.dp, height = 302.dp)
        )
        if (slide != slidesList[0]) {
            Spacer(modifier = Modifier.height(60.dp))
                Text(
                    text = slide.title,
                    fontSize = 34.sp,
                    fontFamily = newPeninimMTFontFamily,
                    color = customBlockColor,
                    lineHeight = 42.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = slide.description,
                    fontSize = 16.sp,
                    fontFamily = newPeninimMTFontFamily,
                    color = customSubTextLightColor,
                    textAlign = TextAlign.Center
                )
        }
        Spacer(modifier = Modifier.height(40.dp))
        OnboardingSlider(
            currentSlide = slide,
            slidesList = slidesList
        )
    }
}

@Composable
fun OnboardingSlider(
    currentSlide: OnboardingSlide,
    slidesList: List<OnboardingSlide>,
    modifier: Modifier = Modifier
) {
    Row {
        slidesList.forEach { slide ->
            Box(
                modifier = modifier
                    .padding(horizontal = 6.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        if (currentSlide == slide)
                            customBlockColor
                        else
                            customDisableColor
                    )
                    .animateContentSize(spring())
                    .height(5.dp)
                    .width(if (currentSlide == slide) 43.dp else 28.dp,)
            )
        }
    }
}