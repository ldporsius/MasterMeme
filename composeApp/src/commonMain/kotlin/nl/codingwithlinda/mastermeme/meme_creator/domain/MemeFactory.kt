package nl.codingwithlinda.mastermeme.meme_creator.domain

import nl.codingwithlinda.mastermeme.core.domain.model.memes.Meme
import nl.codingwithlinda.mastermeme.core.domain.model.memes.MemeText

interface MemeFactory {
    fun createMeme(
        name : String = "",
        imageUri: String,
        isFavorite: Boolean,
        texts: List<MemeText>
    ): Meme

}