package nl.codingwithlinda.mastermeme.navigation

import kotlinx.serialization.Serializable
import nl.codingwithlinda.mastermeme.memes_list.presentation.home_screen.top_bar.MemeSortOption

sealed interface Route {

    @Serializable
    object mainGraph: Route

    @Serializable
    data object MemeList: Route

    @Serializable
    data class MemeCreator(
        val memeId: String
    ): Route

    @Serializable
    data class MemeSelect(
        val memeId: String,
        val sortOption: MemeSortOption
    ): Route

}