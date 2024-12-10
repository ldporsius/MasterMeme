package local_storage.room.dto

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import local_storage.room.model.MemeTextEntity
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
        textColor = textColor.toArgb(),
        offsetX = offsetX,
        offsetY = offsetY,
        parentWidth = parentWidth,
        parentHeight = parentHeight
    )
}

fun MemeTextEntity.toDomain(): MemeText{
    return MemeText(
        id = primaryKey,
        text = text,
        fontResource = fontResource,
        fontSize = fontSize,
        textColor = Color(textColor),
        offsetX = offsetX,
        offsetY = offsetY,
        parentWidth = parentWidth,
        parentHeight = parentHeight

    )
}