package nl.codingwithlinda.mastermeme.meme_save.presentation.state

import nl.codingwithlinda.mastermeme.core.presentation.model.MemeImageUi
import nl.codingwithlinda.mastermeme.meme_creator.presentation.ui_model.MemeUiText

data class MemeSaveViewState(
    val memeImageUi: MemeImageUi? = null,
    val memeTexts: List<MemeUiText> = emptyList()
)
