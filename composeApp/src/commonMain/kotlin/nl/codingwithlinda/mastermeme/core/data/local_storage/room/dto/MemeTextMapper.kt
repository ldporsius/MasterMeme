package nl.codingwithlinda.mastermeme.core.data.local_storage.room.dto

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import nl.codingwithlinda.mastermeme.core.data.local_storage.room.model.MemeTextEntity
import nl.codingwithlinda.mastermeme.core.domain.model.memes.MemeText

fun MemeText.toMemeTextEntity(
    memeId: String,
    imageUri: String): MemeTextEntity {
    return MemeTextEntity(
        memeId = memeId,
        imageUri = imageUri,
        text = text,
        fontResource = fontResource,
        fontSize = fontSize,
        textColor = textColor,
        offsetX = offsetX,
        offsetY = offsetY,

    )
}

fun MemeTextEntity.toDomain(): MemeText{
    return MemeText(
        id = pKey,
        text = text,
        fontResource = fontResource,
        fontSize = fontSize,
        textColor = textColor,
        offsetX = offsetX,
        offsetY = offsetY,


    )
}