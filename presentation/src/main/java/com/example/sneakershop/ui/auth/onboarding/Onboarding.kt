package com.example.sneakershop.ui.auth.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import com.example.sneakershop.datasource.SneakersDatasource
import com.example.sneakershop.model.OnboardingSlide
import com.example.sneakershop.ui.components.CustomButton
import com.example.sneakershop.ui.theme.customBlockColor
import com.example.sneakershop.ui.theme.customDisableColor
import com.example.sneakershop.ui.theme.customSubTextLightColor
import com.example.sneakershop.ui.theme.customTextColor
import com.example.sneakershop.ui.theme.newPeninimMTFontFamily
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Onboarding(
    navigateToHome: () -> Unit
) {

    val slidesList by remember { mutableStateOf(SneakersDatasource.onboardingSlides) }

    val gradient = Brush.linearGradient(colors = listOf(
        Color(72, 178, 231),
        Color(68, 169, 220),
        Color(43, 107, 139)
    ))

    val scope = rememberCoroutineScope()

    val pagerState = rememberPagerState { slidesList.size }

    var visible by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        pagerState.scrollToPage(0)
    }

    Box(
        modifier = Modifier
            .background(gradient)
            .fillMaxSize()
    ) {
        OnboardingPager(
            visible = visible,
            pagerState = pagerState,
            slidesList = slidesList
        )
        OnboardingSlider(
            currentSlide = slidesList[pagerState.currentPage],
            slidesList = slidesList,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = if(pagerState.currentPage == 0) 300.dp else 380.dp)
        )
        CustomButton(
            onClick = {
                if(pagerState.currentPage != slidesList.lastIndex)
                    scope.launch {
                        visible = false
                        delay(100)
                        pagerState.scrollToPage(pagerState.currentPage + 1)
                        delay(100)
                        visible = true
                    }
                else
                    navigateToHome()
            },
            color = customBlockColor,
            text = slidesList[pagerState.currentPage].buttonText,
            textColor = customTextColor,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = 20.dp)
                .padding(bottom = 40.dp)
        )
    }
}

@Composable
fun OnboardingPager(
    visible: Boolean,
    pagerState: PagerState,
    slidesList: List<OnboardingSlide>
) {
    HorizontalPager(
        state = pagerState,
        userScrollEnabled = false
    ) {
        OnboardingElement(
            visible = visible,
            slide = slidesList[it],
            slidesList = slidesList
        )
    }
}

@Composable
fun OnboardingElement(
    visible: Boolean,
    slide: OnboardingSlide,
    slidesList: List<OnboardingSlide>
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(tween(500)),
        exit = fadeOut(tween(500))
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 70.dp)
        ) {
            if (slide == slidesList[0]) {
                Text(
                    text = slide.title,
                    fontSize = 30.sp,
                    fontFamily = newPeninimMTFontFamily,
                    color = customBlockColor,
                    textAlign = TextAlign.Center,
                    lineHeight = 32.sp
                )
                Spacer(modifier = Modifier.height(100.dp))
            }
            Image(
                painter = painterResource(id = slide.image),
                contentDescription = null,
                modifier = Modifier
                    .size(width = 375.dp, height = 302.dp)
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
        }
    }
}

@Composable
fun OnboardingSlider(
    currentSlide: OnboardingSlide,
    slidesList: List<OnboardingSlide>,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        slidesList.forEach { slide ->
            Box(
                modifier = Modifier
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
                    .width(if (currentSlide == slide) 43.dp else 28.dp)
            )
        }
    }
}

@Preview
@Composable
fun OnboardingPreview() {
    Onboarding {}
}