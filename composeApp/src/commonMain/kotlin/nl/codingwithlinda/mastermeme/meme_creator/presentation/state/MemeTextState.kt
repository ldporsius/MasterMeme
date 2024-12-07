package nl.codingwithlinda.mastermeme.meme_creator.presentation.state

import nl.codingwithlinda.mastermeme.meme_creator.presentation.ui_model.MemeUiText

sealed interface MemeTextState {
    data object Idle : MemeTextState
    data object Editing : MemeTextState
    data object Selecting : MemeTextState
}
fun Map<Int, MemeUiText>.changeState(state: MemeTextState, id: Int): Map<Int, MemeUiText>{
    return this.mapValues {
        val _state = if (it.key == id) {
            state
        } else {
            MemeTextState.Idle
        }
        it.value.copy(
            memeTextState = _state)
    }
}
