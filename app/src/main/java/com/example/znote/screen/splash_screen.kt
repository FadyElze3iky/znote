package com.example.znote.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.znote.R
import kotlinx.coroutines.delay

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    // Control visibility of the image
    var visible by remember { mutableStateOf(false) }
    // Launch the animation when composable is entered
    LaunchedEffect(Unit) {
        visible = true // Trigger visibility
        delay(3000)
        visible = false
        delay(500)
        onTimeout() // Navigate after delay
    }
    // AnimatedVisibility composable to show/hide with animation
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(animationSpec = tween(3000)) + scaleIn(
                initialScale = 0.5f,
                animationSpec = tween(3000)
            ),
            exit = fadeOut(animationSpec = tween(3000)) + scaleOut(
                targetScale = 0.5f,
                animationSpec = tween(3000)
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.znote),
                contentDescription = "AppLogo",
                modifier = Modifier.scale(1f) // Optional: to keep scale at 1 after animation
            )
        }
    }
}
