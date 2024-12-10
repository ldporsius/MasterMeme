package nl.codingwithlinda.mastermeme.meme_creator.presentation.meme_factory

import nl.codingwithlinda.mastermeme.core.domain.model.memes.Meme
import nl.codingwithlinda.mastermeme.core.domain.model.memes.MemeText
import nl.codingwithlinda.mastermeme.core.presentation.util.DateTimeUtils
import nl.codingwithlinda.mastermeme.meme_creator.domain.MemeFactory
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class AndroidMemeFactory(
    private val dateTimeUtils: DateTimeUtils
): MemeFactory {
    @OptIn(ExperimentalUuidApi::class)
    override fun createMeme(
        name: String,
        imageUri: String,
        isFavorite: Boolean,
        texts: List<MemeText>,
    ): Meme {
        return Meme(
            id = Uuid.random().toString(),
            name = name,
            imageUri = imageUri,
            dateCreated = dateTimeUtils.getSystemTimeMillis(),
            isFavorite = isFavorite,
            texts = texts,
        )
    }
}