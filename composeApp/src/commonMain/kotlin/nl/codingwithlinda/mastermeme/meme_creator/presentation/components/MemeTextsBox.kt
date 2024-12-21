package nl.codingwithlinda.mastermeme.meme_creator.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import nl.codingwithlinda.mastermeme.core.presentation.model.MemeImageUi
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorAction
import nl.codingwithlinda.mastermeme.meme_creator.presentation.ui_model.MemeUiText

@Composable
fun MemeTextsBox(
    parentSize: IntSize,
    memeTemplate: MemeImageUi,
    memeTexts: List<MemeUiText>,
    onAction: (MemeCreatorAction) -> Unit,
    modifier: Modifier = Modifier) {


    Box(
        modifier = modifier
    ) {

        memeTemplate.DrawImage()
        memeTexts.onEach { memeText ->
            MemeTextComponent(
                text = memeText,
                platformTextStyle = memeText.platformTextStyle,
                parentSize = parentSize,
                onAction = onAction
            )
        }
    }
}