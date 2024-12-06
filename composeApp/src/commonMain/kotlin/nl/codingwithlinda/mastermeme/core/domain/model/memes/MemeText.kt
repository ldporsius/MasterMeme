package nl.codingwithlinda.mastermeme.core.domain.model.memes

data class MemeText(
    val id: Int,
    val text: String,
    val fontSize: Float,
    val offsetX: Float,
    val offsetY: Float,
    val parentWidth: Float,
    val parentHeight: Float,
)