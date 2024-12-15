package nl.codingwithlinda.mastermeme.memes_list.presentation.state

sealed interface MemeListAction {
    data class CreateNewMeme(val id: String): MemeListAction
    data object ShowMemePicker: MemeListAction
    data object HideMemePicker: MemeListAction
    data class MemeClicked(val id: String): MemeListAction
    data class MemeLongPressed(val id: String): MemeListAction

}