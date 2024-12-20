package nl.codingwithlinda.mastermeme.memes_list.presentation.state

import androidx.compose.runtime.Immutable
import nl.codingwithlinda.mastermeme.core.presentation.templates.MemeTemplateUi
import nl.codingwithlinda.mastermeme.core.presentation.model.MemeUi
import nl.codingwithlinda.mastermeme.core.presentation.util.UiText
import nl.codingwithlinda.mastermeme.memes_list.presentation.home_screen.top_bar.MemeSortOption

@Immutable
data class MemeListViewState(
    val memes: List<MemeUi> = emptyList(),
    val templates: List<MemeTemplateUi> = emptyList(),
    val isLoading: Boolean = true,
    val error: UiText? = null,
    val showMemePicker: Boolean = false,
    val interaction: MemeListInteraction = MemeListInteraction.SORTING,
    val selectedSortOption: MemeSortOption = MemeSortOption.FavoritesFirst,
    val searchQuery: String = "",
    val selectedMemes: List<String> = emptyList(),
){

    val selectedMemesCount: Int = selectedMemes.size

    fun isSelected(memeId: String): Boolean {
        return selectedMemes.contains(memeId)
    }

    fun memeUris(): List<String> = memes.filter { it.id in selectedMemes }.map { it.imageUri }

    val sortOptions = MemeSortOption.entries.toList()

    val sortedMemes: List<MemeUi> = when(selectedSortOption){
        MemeSortOption.FavoritesFirst -> {
            memes.sortedByDescending { it.isFavorite }
        }
        MemeSortOption.NewestFirst -> {
            memes.sortedByDescending { it.dateCreated}
        }
        MemeSortOption.Both -> {
            memes.filter { it.isFavorite }.sortedByDescending { it.dateCreated }
                .plus(memes.filterNot { it.isFavorite }.sortedByDescending { it.dateCreated })
        }
    }
    val searchResult = templates.filter {
        it.name.contains(searchQuery, ignoreCase = true)
    }
}
