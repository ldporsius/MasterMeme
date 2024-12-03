package nl.codingwithlinda.mastermeme.meme_creator.presentation.state

sealed interface MemeCreatorAction {
    data object SaveMeme : MemeCreatorAction
    data object AddText : MemeCreatorAction
    data object StartEditing : MemeCreatorAction
    data object StopEditing : MemeCreatorAction
    data class EditMemeText(val text: String) : MemeCreatorAction
    data class SelectMemeText(val index: Int) : MemeCreatorAction
}