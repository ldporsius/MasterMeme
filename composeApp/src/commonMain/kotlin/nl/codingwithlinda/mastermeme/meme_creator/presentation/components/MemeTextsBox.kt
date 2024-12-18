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
import nl.codingwithlinda.mastermeme.meme_creator.presentation.ui_model.MemeUiText

@Composable
fun MemeTextsBox(
    memeTemplate: MemeImageUi,
    memeTexts: List<MemeUiText>,
    onAction: (MemeCreatorAction) -> Unit,
    modifier: Modifier = Modifier) {

    //val h = memeTemplate.height().toFloat()
    //val w = memeTemplate.width().toFloat()
    //println("MEME TEXTS BOX. H: $h , W: $w")
   // println("MEME TEXTS BOX. aspect ratio: ${w / h}")

    var size by remember {
        mutableStateOf( Size.Zero )
    }
    Box(
        modifier = modifier
            .onSizeChanged {
                size = Size(it.width.toFloat(), it.height.toFloat())
            }

    ) {

        memeTemplate.DrawImage()
        memeTexts.onEach { memeText ->
            MemeTextComponent(
                text = memeText,
                platformTextStyle = memeText.platformTextStyle,
                parentSize = Size(
                    width = size.width,
                    height = size.height
                ),
                onAction = onAction
            )
        }
    }
}