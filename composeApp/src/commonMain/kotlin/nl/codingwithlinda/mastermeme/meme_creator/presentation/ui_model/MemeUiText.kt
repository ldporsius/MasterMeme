package nl.codingwithlinda.mastermeme.meme_creator.presentation.ui_model

data class MemeUiText(
    val id: Int,
    val text: String,
    val offsetX: Float,
    val offsetY: Float,
    val parentWidth: Float,
    val parentHeight: Float,
    val isEditing: Boolean = false
)
