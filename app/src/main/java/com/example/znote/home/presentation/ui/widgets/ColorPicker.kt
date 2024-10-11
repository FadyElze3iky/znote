package com.example.znote.home.presentation.ui.widgets

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.max
import kotlin.math.min

@Composable
fun ColorPicker(
    initialColor: Color = Color(0x44650D21),
    onColorSelected: (Color) -> Unit,
    modifier: Modifier = Modifier,
) {
    var thumbPosition by remember { mutableStateOf(Offset(0f, 0f)) }
    var selectedColor by remember { mutableStateOf(initialColor) }

    Box(modifier = modifier) {
        Canvas(modifier = Modifier
            .size(200.dp)
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()

                    // Update the thumb position based on drag
                    val newOffset = Offset(
                        x = max(0f, min(200f, thumbPosition.x + dragAmount.x)),
                        y = max(0f, min(200f, thumbPosition.y + dragAmount.y))
                    )
                    thumbPosition = newOffset

                    // Get the color at the touch position
                    val color = getColorAtPosition(newOffset, 200.dp.toPx())
                    selectedColor = color
                    onColorSelected(selectedColor)
                }
            }) {
            // Draw a gradient from black to white and from black to green
            drawRect(
                brush = androidx.compose.ui.graphics.Brush.verticalGradient(
                    colors = listOf(Color.Black, Color.Green)
                )
            )
            drawRect(
                brush = androidx.compose.ui.graphics.Brush.horizontalGradient(
                    colors = listOf(Color.Transparent, Color.White),
                    startX = size.width,
                    endX = 0f
                )
            )
        }

        // Draw the thumb
        Box(
            modifier = Modifier

                .offset { IntOffset(thumbPosition.x.toInt() - 10, thumbPosition.y.toInt() - 10) }
                .size(20.dp)
                .background(Color.White, shape = MaterialTheme.shapes.medium)
        )
    }

    // Set the initial thumb position to the center
    LaunchedEffect(Unit) {
        thumbPosition = Offset(100f, 100f) // Center of the box
    }
}

private fun getColorAtPosition(offset: Offset, size: Float): Color {
    val normalizedX = (offset.x / size).coerceIn(0f, 1f)
    val normalizedY = (offset.y / size).coerceIn(0f, 1f)

    // Interpolate colors based on position
    val r = (1 - normalizedY) * 255 // black to green (0, 255, 0)
    val g = (normalizedY) * 255 // black to green
    val b = (1 - normalizedX) * 255 // white to black

    return Color(r.toInt(), g.toInt(), b.toInt())
}

