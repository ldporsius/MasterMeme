package nl.codingwithlinda.mastermeme.meme_creator.presentation.ui_model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Typeface
import nl.codingwithlinda.mastermeme.core.domain.Memento
import nl.codingwithlinda.mastermeme.core.presentation.model.FontUi
import nl.codingwithlinda.mastermeme.meme_creator.presentation.memento.MemeTextMemento
import nl.codingwithlinda.mastermeme.meme_creator.presentation.memento.MementoCareTaker
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeTextState

data class MemeUiText(
    val id: Int,
    val text: String,
    val fontResource: FontUi,
    val fontSize: Float,
    val textColor: Color,
    val offsetX: Float,
    val offsetY: Float,
    val memeTextState: MemeTextState = MemeTextState.Idle,
    ) {

    fun saveState(): Memento<MemeUiText> {
       return MemeTextMemento(this)
    }

    fun restoreState(careTaker: MementoCareTaker<MemeUiText>): MemeUiText {
        return careTaker.undo() ?: this
    }
}
