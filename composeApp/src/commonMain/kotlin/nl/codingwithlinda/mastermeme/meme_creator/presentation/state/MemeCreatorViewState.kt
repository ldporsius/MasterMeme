package nl.codingwithlinda.mastermeme.meme_creator.presentation.state

import nl.codingwithlinda.mastermeme.core.presentation.model.MemeImageUi
import nl.codingwithlinda.mastermeme.meme_creator.presentation.ui_model.MemeUiText

data class MemeCreatorViewState(
    val memeImageUi: MemeImageUi,
    val memeTexts: Map<Int, MemeUiText> = emptyMap(),
    val isSaving: Boolean = false,
    val memeUri: String? = null
){
    val selectedMemeText: MemeUiText? = memeTexts.values.find { it.memeTextState == MemeTextState.Selected }
    val editingMemeText: MemeUiText? = memeTexts.values.find { it.memeTextState == MemeTextState.Editing }

    val isEditing: Boolean = editingMemeText != null
    val isSelecting: Boolean = selectedMemeText != null

    val shouldShowEditTextOption = isSelecting


}
