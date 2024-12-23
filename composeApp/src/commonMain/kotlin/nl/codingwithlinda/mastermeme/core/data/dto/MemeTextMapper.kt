package nl.codingwithlinda.mastermeme.core.data.dto

import androidx.compose.ui.graphics.toArgb
import nl.codingwithlinda.mastermeme.core.domain.model.memes.MemeText
import nl.codingwithlinda.mastermeme.meme_creator.presentation.ui_model.MemeUiText

fun MemeUiText.toDomain(): MemeText{
    return MemeText(
        id = this.id,
        text = text,
        offsetX = offsetX,
        offsetY = offsetY,
        fontResource = fontResource.ref,
        fontSize = fontSize,
        textColor = textColor.toArgb(),
        rotation = rotation,

    )
}