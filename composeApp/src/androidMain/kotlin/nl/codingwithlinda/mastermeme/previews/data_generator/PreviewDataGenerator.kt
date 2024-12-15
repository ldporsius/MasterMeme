package nl.codingwithlinda.mastermeme.previews.data_generator

import androidx.compose.ui.text.font.Font
import mastermeme.composeapp.generated.resources.Res
import mastermeme.composeapp.generated.resources._1_49su9f
import nl.codingwithlinda.mastermeme.R
import nl.codingwithlinda.mastermeme.core.presentation.create_meme.OurPlatformTextStyle
import nl.codingwithlinda.mastermeme.core.presentation.model.FontUi
import nl.codingwithlinda.mastermeme.core.presentation.model.MemeImageUi
import nl.codingwithlinda.mastermeme.core.presentation.model.MemeUi
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
        platformTextStyle = OurPlatformTextStyle(),
        offsetX = 0f,
        offsetY = 0f,


    )
}

fun fakeMemeUi(): MemeUi {
   return MemeUi(
        id = "1",
        name = "meme1",
        dateCreated = 123456789,
        image = MemeImageUi.pngImage(Res.drawable._1_49su9f),
        isFavorite = true
    )
}