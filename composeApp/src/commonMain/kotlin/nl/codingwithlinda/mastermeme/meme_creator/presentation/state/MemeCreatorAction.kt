package nl.codingwithlinda.mastermeme.meme_creator.presentation.state

sealed interface MemeCreatorAction {
    data object StartSaveMeme : MemeCreatorAction
    data object CancelSaveMeme : MemeCreatorAction
    data object SaveMeme : MemeCreatorAction
    data object ShareMeme : MemeCreatorAction
    data object AddText : MemeCreatorAction
    data class CreateText(val text: String) : MemeCreatorAction
    data class PositionText(val id: Int, val parentWidth: Float, val parentHeight: Float, val offsetX: Float, val offsetY: Float) : MemeCreatorAction
    data class StartEditing(val id: Int) : MemeCreatorAction
    data object StopEditing : MemeCreatorAction
    data class EditMemeText(val id: Int, val text: String) : MemeCreatorAction
    data class UndoEditing(val id: Int) : MemeCreatorAction
    data class SelectMemeText(val id: Int) : MemeCreatorAction
    data class DeleteMemeText(val id: Int) : MemeCreatorAction
    data class SaveParentSize(val width: Float, val height: Float) : MemeCreatorAction
    data class AdjustTextSize(val id: Int, val size: Float) : MemeCreatorAction
    data class EditMemeTextColor(val id: Int, val color: androidx.compose.ui.graphics.Color) : MemeCreatorAction
    data class EditMemeTextFont(val id: Int, val fontIndex: Int) : MemeCreatorAction
    data object Undo : MemeCreatorAction
    data object UndoAll : MemeCreatorAction

}