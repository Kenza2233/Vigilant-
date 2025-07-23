package com.example.vinote.ui.components

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedSettingsIcon(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface
) {
    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000)
        )
    )

    Canvas(modifier = modifier.size(24.dp)) {
        rotate(rotation) {
            drawCircle(
                color = color,
                radius = size.minDimension / 4
            )
            for (i in 0..7) {
                rotate(i * 45f) {
                    drawLine(
                        color = color,
                        start = Offset(size.center.x, size.center.y - size.minDimension / 3),
                        end = Offset(size.center.x, size.center.y - size.minDimension / 2),
                        strokeWidth = 4f
                    )
                }
            }
        }
    }
}
