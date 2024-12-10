package local_storage.room.dto

import local_storage.room.model.MemeTextEntity
import nl.codingwithlinda.mastermeme.core.domain.model.memes.MemeText

fun MemeText.toMemeTextEntity(imageUrl: String): MemeTextEntity {
    return MemeTextEntity(
        imageUri = imageUrl,
        id = id,
        text = text,
        fontResource = fontResource,
        fontSize = fontSize,
        textColor = textColor,
        offsetX = offsetX,
        offsetY = offsetY,
        parentWidth = parentWidth,
        parentHeight = parentHeight
    )

}

fun MemeTextEntity.toDomain(): MemeText{
    return MemeText(
        id = id,
        text = text,
        fontResource = fontResource,
        fontSize = fontSize,
        textColor = textColor,
        offsetX = offsetX,
        offsetY = offsetY,
        parentWidth = parentWidth,
        parentHeight = parentHeight

    )
}