package nl.codingwithlinda.mastermeme.previews

import nl.codingwithlinda.mastermeme.meme_creator.presentation.ui_model.MemeUiText

fun fakeMemeTextUI(): MemeUiText{
    return MemeUiText(
        id = 0,
        text = "Fake text",
        fontSize = 20f,
        textColor = androidx.compose.ui.graphics.Color.Black,
        offsetX = 0f,
        offsetY = 0f,
        parentWidth = 0f,
        parentHeight = 0f


    )
}