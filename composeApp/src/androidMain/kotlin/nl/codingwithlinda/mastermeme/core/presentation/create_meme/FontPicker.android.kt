package nl.codingwithlinda.mastermeme.core.presentation.create_meme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import nl.codingwithlinda.mastermeme.R
import nl.codingwithlinda.mastermeme.core.presentation.model.FontUi

actual class FontPicker {

    actual val fontResources: List<FontUi>
        get() = listOf(
            FontUi(
                ref = R.font.impact,
                name = "Impact",
                font = Font( resId = R. font.impact,
                    weight = FontWeight.Normal,
                    style = FontStyle.Normal)
            ),
            FontUi(
                ref = R.font.impact,
                name = "Impact",
                font = Font( resId = R. font.impact,
                    weight = FontWeight.ExtraBold,
                    style = FontStyle.Italic)
            ),
            FontUi(
                ref = R.font.roboto,
                name = "Roboto",
                font = Font( resId = R. font.roboto,
                    weight = FontWeight.Normal,
                    style = FontStyle.Normal)
            ),

        )

    actual fun getFont(index: Int): Int{
        return fontResources[index].ref
    }
}