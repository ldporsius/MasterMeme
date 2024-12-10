package local_storage.room.dto

import local_storage.room.model.MemeEntity
import nl.codingwithlinda.mastermeme.core.domain.model.memes.Meme
import nl.codingwithlinda.mastermeme.core.domain.model.memes.MemeText

fun Meme.toMemeEntity(): MemeEntity {
    return MemeEntity(
        id = this.id,
        name = this.name,
        imageUri = this.imageUri,
        dateCreated = this.dateCreated,
        isFavorite = false
    )
}

fun MemeEntity.toMeme(
    texts: List<MemeText>
): Meme {
    return Meme(
        id = this.id,
        name = this.name,
        imageUri = this.imageUri,
        dateCreated = this.dateCreated,
        isFavorite = isFavorite,
        texts = texts
    )
}