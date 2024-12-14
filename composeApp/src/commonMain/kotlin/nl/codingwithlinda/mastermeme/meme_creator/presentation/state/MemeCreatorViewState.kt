package nl.codingwithlinda.mastermeme.meme_creator.presentation.state

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.text.PlatformTextStyle
import nl.codingwithlinda.mastermeme.core.domain.model.templates.MemeTemplate
import nl.codingwithlinda.mastermeme.core.presentation.model.MemeImageUi
import nl.codingwithlinda.mastermeme.meme_creator.presentation.components.MemeTextComponent
import nl.codingwithlinda.mastermeme.meme_creator.presentation.ui_model.MemeUiText

data class MemeCreatorViewState(
    val memeImageUi: MemeImageUi,
    val memeTexts: Map<Int, MemeUiText> = emptyMap(),
    val isAddingText: Boolean = false,
    val isSaving: Boolean = false,
    val memeUri: String? = null
){
    val selectedMemeText: MemeUiText? = memeTexts.values.find { it.memeTextState == MemeTextState.Selected }
    val editingMemeText: MemeUiText? = memeTexts.values.find { it.memeTextState == MemeTextState.Editing }

    private val isSelecting: Boolean = selectedMemeText != null

    val shouldShowEditTextOption = isSelecting


    @Composable
    fun PictureDrawerContent(
        modifier: Modifier = Modifier,
        onAction: (MemeCreatorAction) -> Unit,
    ) {
        BoxWithConstraints(
            modifier = Modifier
        ) {
            memeImageUi.DrawImage()
            memeTexts.onEach { memeText ->
                MemeTextComponent(
                    text = memeText.value,
                    platformTextStyle = memeText.value.platformTextStyle,
                    parentSize = Size(
                        constraints.maxWidth.toFloat(),
                        constraints.maxHeight.toFloat()
                    ),
                    onAction = onAction
                )
            }
        }
    }
}
