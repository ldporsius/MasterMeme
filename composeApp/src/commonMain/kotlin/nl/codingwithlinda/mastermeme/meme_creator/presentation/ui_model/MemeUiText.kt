package nl.codingwithlinda.mastermeme.meme_creator.presentation.ui_model

import nl.codingwithlinda.mastermeme.core.domain.Memento
import nl.codingwithlinda.mastermeme.meme_creator.presentation.memento.MemeTextMemento
import nl.codingwithlinda.mastermeme.meme_creator.presentation.memento.MementoCareTaker

data class MemeUiText(
    val id: Int,
    val text: String,
    val fontSize: Float,
    val offsetX: Float,
    val offsetY: Float,
    val parentWidth: Float,
    val parentHeight: Float,
    val isEditing: Boolean = false
) {

    fun saveState(): Memento<MemeUiText> {
       return MemeTextMemento(this)
    }

    fun restoreState(careTaker: MementoCareTaker<MemeUiText>): MemeUiText {
        println("CARETAKER RESTORED CALLED. ")
        return careTaker.undo() ?: this

    }
}
