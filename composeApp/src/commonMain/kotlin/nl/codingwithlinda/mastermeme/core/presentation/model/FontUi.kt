package nl.codingwithlinda.mastermeme.core.presentation.model

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.style.TextDecoration

data class FontUi(
    val ref: Int,
    val name: String,
    val font: Font,
    val textDecoration: TextDecoration = TextDecoration.None
)
