package nl.codingwithlinda.mastermeme.meme_select.presentation.components

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import nl.codingwithlinda.mastermeme.core.presentation.model.MemeImageUi
import nl.codingwithlinda.mastermeme.meme_creator.presentation.components.MemeTextComponent
import nl.codingwithlinda.mastermeme.meme_creator.presentation.ui_model.MemeUiText

@Composable
fun PictureDrawerContent(
    modifier: Modifier = Modifier,
    memeImageUi: MemeImageUi,
    memeTexts: List<MemeUiText>,
) {
    BoxWithConstraints(
        modifier = modifier
    ) {
        memeImageUi.DrawImage()
        memeTexts.onEach { memeText ->
            MemeTextComponent(
                text = memeText,
                platformTextStyle = memeText.platformTextStyle,
                parentSize = Size(
                    constraints.maxWidth.toFloat(),
                    constraints.maxHeight.toFloat()
                ),
                onAction = {}
            )
        }
    }
}