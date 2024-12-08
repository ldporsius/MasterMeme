package nl.codingwithlinda.mastermeme.core.domain.model.memes

import androidx.compose.ui.graphics.Color

data class MemeText(
    val id: Int,
    val text: String,
    val fontSize: Float,
    val textColor: Color,
    val offsetX: Float,
    val offsetY: Float,
    val parentWidth: Float,
    val parentHeight: Float,
)