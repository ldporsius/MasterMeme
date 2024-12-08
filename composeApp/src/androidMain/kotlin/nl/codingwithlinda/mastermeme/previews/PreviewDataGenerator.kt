package nl.codingwithlinda.mastermeme.previews

import androidx.compose.ui.text.font.Font
import nl.codingwithlinda.mastermeme.R
import nl.codingwithlinda.mastermeme.core.presentation.model.FontUi
import nl.codingwithlinda.mastermeme.meme_creator.presentation.ui_model.MemeUiText

fun fakeMemeTextUI(): MemeUiText{
    return MemeUiText(
        id = 0,
        text = "Fake text",
        fontResource = FontUi(
            ref = R.font.impact,
            name = "Impact",
            font = Font(
                resId = R.font.impact,
                weight = androidx.compose.ui.text.font.FontWeight.Normal,
                style = androidx.compose.ui.text.font.FontStyle.Normal
            )
        ),
        fontSize = 20f,
        textColor = androidx.compose.ui.graphics.Color.Black,
        offsetX = 0f,
        offsetY = 0f,
        parentWidth = 0f,
        parentHeight = 0f


    )
}