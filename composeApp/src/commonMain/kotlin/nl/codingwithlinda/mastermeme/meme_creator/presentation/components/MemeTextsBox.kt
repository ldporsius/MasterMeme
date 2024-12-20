package nl.codingwithlinda.mastermeme.meme_creator.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.mastermeme.core.presentation.model.MemeImageUi
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorAction
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorViewState
import nl.codingwithlinda.mastermeme.meme_creator.presentation.ui_model.MemeUiText

@Composable
fun MemeTextsBox(
    parentSize: Size,
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