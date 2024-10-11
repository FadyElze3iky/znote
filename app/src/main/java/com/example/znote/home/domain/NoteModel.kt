package com.example.znote.home.domain

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

data class Note(
    val id: String,
    var content: String,
    val date: String,
    var color: Int = Color(0xffd7e6f9).toArgb(),
)