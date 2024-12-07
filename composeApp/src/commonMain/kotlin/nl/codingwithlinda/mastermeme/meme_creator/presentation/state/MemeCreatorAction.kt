package nl.codingwithlinda.mastermeme.meme_creator.presentation.state

sealed interface MemeCreatorAction {
    data object StartSaveMeme : MemeCreatorAction
    data object CancelSaveMeme : MemeCreatorAction
    data object SaveMeme : MemeCreatorAction
    data object ShareMeme : MemeCreatorAction
    data object AddText : MemeCreatorAction
    data class PositionText(val id: Int, val parentWidth: Float, val parentHeight: Float, val offsetX: Float, val offsetY: Float) : MemeCreatorAction
    data object StopEditing : MemeCreatorAction
    data class EditMemeText(val id: Int, val text: String) : MemeCreatorAction
    data class SelectMemeText(val index: Int) : MemeCreatorAction
    data class DeleteMemeText(val index: Int) : MemeCreatorAction
    data class SaveParentSize(val width: Float, val height: Float) : MemeCreatorAction
    data class AdjustTextSize(val id: Int, val size: Float) : MemeCreatorAction
    data class UndoTextSize(val id: Int) : MemeCreatorAction

}