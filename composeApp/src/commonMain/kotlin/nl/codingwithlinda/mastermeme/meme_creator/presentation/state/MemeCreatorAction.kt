package nl.codingwithlinda.mastermeme.meme_creator.presentation.state

sealed interface MemeCreatorAction {
    object SaveMeme : MemeCreatorAction
    object AddText : MemeCreatorAction
}