package com.example.sportapp.shared.modifierExtensions

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.dropShadow(
    color: Color = Color.Black.copy(alpha = 0.3f),
    blurRadius: Dp = 16.dp,
    offsetX: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    shadowHeight: Dp = 16.dp
) = this.drawBehind {
    val paint = Paint().apply {
        this.color = color
        this.isAntiAlias = true
        this.asFrameworkPaint().apply {
            maskFilter = android.graphics.BlurMaskFilter(blurRadius.toPx(), android.graphics.BlurMaskFilter.Blur.NORMAL)
        }
    }

    val shadowOffsetX = offsetX.toPx()
    val shadowOffsetY = offsetY.toPx()
    val shadowHeightPx = shadowHeight.toPx()

    drawIntoCanvas { canvas ->
        canvas.save()
        canvas.translate(shadowOffsetX, shadowOffsetY)

        canvas.drawRect(
            Rect(
                left = 0f,
                top = 4f,
                right = size.width,
                bottom = shadowHeightPx
            ),
            paint
        )
        canvas.restore()
    }
}