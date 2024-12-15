package nl.codingwithlinda.mastermeme.memes_list.presentation.home_screen.top_bar

import nl.codingwithlinda.mastermeme.core.presentation.util.UiText

val memeSortOptionToUiText = mapOf(
    MemeSortOption.FavoritesFirst to UiText.DynamicString("Favorites first"),
    MemeSortOption.NewestFirst to UiText.DynamicString("Newest first"),
    MemeSortOption.Both to UiText.DynamicString("Both")

)