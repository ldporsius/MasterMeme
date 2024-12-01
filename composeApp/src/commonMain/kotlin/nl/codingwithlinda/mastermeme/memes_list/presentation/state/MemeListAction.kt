package nl.codingwithlinda.mastermeme.memes_list.presentation.state

sealed interface MemeListAction {
    data object CreateNewMeme: MemeListAction
}