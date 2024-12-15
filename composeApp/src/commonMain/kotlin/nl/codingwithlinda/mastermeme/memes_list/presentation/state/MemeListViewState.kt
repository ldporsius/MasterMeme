package nl.codingwithlinda.mastermeme.memes_list.presentation.state

import androidx.compose.runtime.Immutable
import nl.codingwithlinda.mastermeme.core.presentation.templates.MemeTemplateUi
import nl.codingwithlinda.mastermeme.core.presentation.model.MemeUi
import nl.codingwithlinda.mastermeme.memes_list.presentation.home_screen.top_bar.MemeSortOption

@Immutable
data class MemeListViewState(
    val memes: List<MemeUi> = emptyList(),
    val templates: List<MemeTemplateUi> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val showMemePicker: Boolean = false,
    val interaction: MemeListInteraction = MemeListInteraction.SORTING,
    val selectedSortOption: MemeSortOption = MemeSortOption.FavoritesFirst
){
    val sortOptions = MemeSortOption.entries.toList()
}
