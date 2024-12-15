package nl.codingwithlinda.mastermeme.meme_select.presentation.state

import nl.codingwithlinda.mastermeme.core.presentation.model.MemeUi

data class MemeSelectViewState(
    val memes: List<MemeUi> = emptyList(),

    )
