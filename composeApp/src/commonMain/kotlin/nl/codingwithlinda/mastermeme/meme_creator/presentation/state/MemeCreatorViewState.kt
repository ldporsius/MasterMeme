package nl.codingwithlinda.mastermeme.meme_creator.presentation.state

import nl.codingwithlinda.mastermeme.core.presentation.MemeImageUi

data class MemeCreatorViewState(
   val memeImageUi: MemeImageUi,
   val isEditing: Boolean = false,
   val memeTexts: List<String> = emptyList(),
   val selectedMemeTextIndex: Int = -1
)
