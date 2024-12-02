package nl.codingwithlinda.mastermeme.meme_creator.presentation.state

sealed interface MemeCreatorAction {
    data object SaveMeme : MemeCreatorAction
    data object AddText : MemeCreatorAction
    data object StopEditing : MemeCreatorAction
}