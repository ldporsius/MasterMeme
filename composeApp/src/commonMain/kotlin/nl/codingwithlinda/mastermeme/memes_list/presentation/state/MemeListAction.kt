package nl.codingwithlinda.mastermeme.memes_list.presentation.state

import nl.codingwithlinda.mastermeme.memes_list.presentation.home_screen.top_bar.MemeSortOption

sealed interface MemeListAction {
    data class CreateNewMeme(val id: String): MemeListAction
    data object ShowMemePicker: MemeListAction
    data object HideMemePicker: MemeListAction
    data class MemeClicked(val id: String): MemeListAction
    data class MemeLongPressed(val id: String): MemeListAction
    data class SortMemes(val sortOption: MemeSortOption): MemeListAction
    data class SearchTemplates(val query: String): MemeListAction
}
