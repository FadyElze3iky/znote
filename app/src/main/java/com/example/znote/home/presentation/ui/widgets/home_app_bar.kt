package com.example.znote.home.presentation.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import com.example.znote.R

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeAppBar() {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center) // Center the image in the Box
            ) {
                Image(
                    painter = painterResource(id = R.drawable.znote), // Your image resource
                    contentDescription = "App Logo",
                    Modifier.scale(0.5f)
                )
            }
        },
    )
}