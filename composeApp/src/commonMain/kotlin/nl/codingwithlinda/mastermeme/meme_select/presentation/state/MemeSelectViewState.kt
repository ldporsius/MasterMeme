package nl.codingwithlinda.mastermeme.meme_select.presentation.state

import nl.codingwithlinda.mastermeme.core.presentation.model.MemeUi
import nl.codingwithlinda.mastermeme.memes_list.presentation.home_screen.top_bar.MemeSortOption

data class MemeSelectViewState(
    val memes: List<MemeUi> = emptyList(),
    val selectedMemes: List<String> = emptyList(),
    val sortOption: MemeSortOption = MemeSortOption.FavoritesFirst,

    ){

    val sortedMemes = when(sortOption){
        MemeSortOption.FavoritesFirst -> memes.sortedByDescending { it.isFavorite }
        MemeSortOption.NewestFirst -> memes.sortedByDescending { it.dateCreated }
        MemeSortOption.Both -> memes.filter { it.isFavorite }.sortedByDescending { it.dateCreated }.plus(memes.filter { !it.isFavorite })
    }
    val selectedMemesCount: Int = selectedMemes.size

    fun isSelected(memeId: String): Boolean {
        return selectedMemes.contains(memeId)
    }

    fun memeUris(): List<String> = memes.filter { it.id in selectedMemes }.map { it.imageUri }
}
