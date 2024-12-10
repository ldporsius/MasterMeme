package nl.codingwithlinda.mastermeme.meme_creator.presentation.meme_factory

import nl.codingwithlinda.mastermeme.core.domain.model.memes.Meme
import nl.codingwithlinda.mastermeme.core.domain.model.memes.MemeText
import nl.codingwithlinda.mastermeme.core.presentation.util.DateTimeUtils
import nl.codingwithlinda.mastermeme.meme_creator.domain.MemeFactory

class AndroidMemeFactory(
    private val dateTimeUtils: DateTimeUtils
): MemeFactory {
    override fun createMeme(
        id: String,
        name: String,
        imageUri: String,
        isFavorite: Boolean,
        texts: List<MemeText>,
    ): Meme {
        return Meme(
            id = id,
            name = name,
            imageUri = imageUri,
            dateCreated = dateTimeUtils.getSystemTimeMillis(),
            isFavorite = isFavorite,
            texts = texts,
        )
    }
}