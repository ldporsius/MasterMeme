package nl.codingwithlinda.mastermeme.memes_list.presentation.state

sealed interface MemeListAction {
    object CreateNewMeme: MemeListAction
}