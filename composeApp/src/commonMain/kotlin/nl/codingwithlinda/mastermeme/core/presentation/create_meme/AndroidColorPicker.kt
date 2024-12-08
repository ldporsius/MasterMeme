package nl.codingwithlinda.mastermeme.core.presentation.create_meme

import androidx.compose.ui.graphics.Color
import nl.codingwithlinda.mastermeme.ui.theme.black
import nl.codingwithlinda.mastermeme.ui.theme.white

class AndroidColorPicker: ColorPicker {
    override val colors: List<Color>
        get() = listOf(
            white,
            Color.Yellow,
            Color.Green,
            Color.Blue,
            Color.Magenta,
            Color.Red,
            black,
        )


}