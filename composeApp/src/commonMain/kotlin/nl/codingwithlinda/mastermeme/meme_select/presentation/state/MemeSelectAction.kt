package nl.codingwithlinda.mastermeme.meme_select.presentation.state

sealed interface MemeSelectAction {
    data class SelectMeme(val memeId: String) : MemeSelectAction
    data object ShareMemes : MemeSelectAction
    data object DeleteMemes : MemeSelectAction

}