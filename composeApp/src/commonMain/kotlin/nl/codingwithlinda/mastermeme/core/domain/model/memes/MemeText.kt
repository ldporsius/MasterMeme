package nl.codingwithlinda.mastermeme.core.domain.model.memes

import kotlinx.serialization.Serializable

@Serializable
data class MemeText(
    val id: Int,
    val text: String,
    val fontResource: Int,
    val fontSize: Float,
    val textColor: Int,
    val offsetX: Float,
    val offsetY: Float,
)