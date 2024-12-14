package nl.codingwithlinda.mastermeme.core.data.dto

import kotlinx.serialization.Serializable
import nl.codingwithlinda.mastermeme.core.domain.model.memes.MemeText

@Serializable
data class MemeDto(
    val parentWidth: Float,
    val parentHeight: Float,
    val imageUri: String,
    val memeTexts: List<MemeText>
)