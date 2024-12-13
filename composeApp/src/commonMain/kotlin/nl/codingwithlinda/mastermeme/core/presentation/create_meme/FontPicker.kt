package nl.codingwithlinda.mastermeme.core.presentation.create_meme

import nl.codingwithlinda.mastermeme.core.presentation.model.FontUi

expect class FontPicker {

    val fontResources : List<FontUi>

    fun getFont(index: Int): Int

}