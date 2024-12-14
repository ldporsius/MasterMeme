package nl.codingwithlinda.mastermeme.meme_creator.presentation.meme_factory

import nl.codingwithlinda.mastermeme.core.domain.model.memes.Meme
import nl.codingwithlinda.mastermeme.core.domain.model.memes.MemeText
import nl.codingwithlinda.mastermeme.core.presentation.create_meme.FontPicker
import nl.codingwithlinda.mastermeme.core.presentation.create_meme.OurPlatformTextStyle
import nl.codingwithlinda.mastermeme.core.presentation.model.FontUi
import nl.codingwithlinda.mastermeme.core.presentation.util.DateTimeUtils
import nl.codingwithlinda.mastermeme.meme_creator.domain.MemeFactory
import nl.codingwithlinda.mastermeme.meme_creator.presentation.ui_model.MemeUiText
import nl.codingwithlinda.mastermeme.ui.theme.black
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class AndroidMemeFactory(
    private val dateTimeUtils: DateTimeUtils,
    private val fontPicker: FontPicker,
    private val ourPlatformTextStyle: OurPlatformTextStyle,
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

    override fun defaultMemeUiText(): MemeUiText {
        return MemeUiText(
            id = 0,
            text = "",
            fontResource = fontPicker.fontResources[0],
            fontSize = 57f,
            textColor = black,
            platformTextStyle = ourPlatformTextStyle,
            offsetX = 0f,
            offsetY = 0f,

        )
    }



}