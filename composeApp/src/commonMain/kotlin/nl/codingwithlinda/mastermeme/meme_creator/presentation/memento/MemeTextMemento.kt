package nl.codingwithlinda.mastermeme.meme_creator.presentation.memento

import nl.codingwithlinda.mastermeme.core.domain.Memento
import nl.codingwithlinda.mastermeme.meme_creator.presentation.ui_model.MemeUiText

class MemeTextMemento(
    val value: MemeUiText
): Memento<MemeUiText> {

    private var _state: MemeUiText? = null

    init {
        _state = value
    }
    override fun restoreState(): MemeUiText? {
       return _state
    }
}