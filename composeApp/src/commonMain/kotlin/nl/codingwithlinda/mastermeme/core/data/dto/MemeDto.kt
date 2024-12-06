package nl.codingwithlinda.mastermeme.core.data.dto

import nl.codingwithlinda.mastermeme.core.domain.model.memes.MemeText

data class MemeDto(
    val imageBytes: ByteArray,
    val memeTexts: List<MemeText>
)