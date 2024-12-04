package nl.codingwithlinda.mastermeme.meme_creator.presentation.state

import nl.codingwithlinda.mastermeme.core.presentation.MemeImageUi
import nl.codingwithlinda.mastermeme.meme_creator.presentation.ui_model.MemeUiText

data class MemeCreatorViewState(
   val memeImageUi: MemeImageUi,
   val isEditing: Boolean = false,
   val memeTexts: Map<Int, MemeUiText> = emptyMap(),
   val selectedMemeTextIndex: Int = -1,

){
   val selectedMemeText: MemeUiText? = memeTexts[selectedMemeTextIndex]
   val newMemeText: MemeUiText? = memeTexts.values.find { it.isEditing }
}
