package nl.codingwithlinda.mastermeme.memes_list.presentation.state

import nl.codingwithlinda.mastermeme.core.presentation.MemeUi

data class MemeListViewState(
    val memes: List<MemeUi> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val showMemePicker: Boolean = false
)
