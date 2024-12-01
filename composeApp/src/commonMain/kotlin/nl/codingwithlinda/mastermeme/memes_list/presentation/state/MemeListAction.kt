package nl.codingwithlinda.mastermeme.memes_list.presentation.state

sealed interface MemeListAction {
    data object CreateNewMeme: MemeListAction
    data object ShowMemePicker: MemeListAction
    data object HideMemePicker: MemeListAction

}