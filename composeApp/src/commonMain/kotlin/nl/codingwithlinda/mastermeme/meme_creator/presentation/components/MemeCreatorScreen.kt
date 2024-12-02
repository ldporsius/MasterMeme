package nl.codingwithlinda.mastermeme.meme_creator.presentation.components

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorAction
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorViewState

@Composable
fun MemeCreatorScreen(
    modifier: Modifier = Modifier,
    state: MemeCreatorViewState,
    onAction: (MemeCreatorAction) -> Unit
) {
    Surface (modifier = modifier){

        val draggable = Modifier.draggable(
            state = rememberDraggableState { },
            orientation = Orientation.Vertical,

        )
        Column {
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)

            ) {
                state.memeImageUi.DrawImage()
            }

            Spacer(modifier = Modifier.weight(1f))

            when(state.isEditing){
                true -> EditTextSizeComponent(
                    onAction = onAction
                )
                false -> {
                    CreatorButtonsComponent(
                        modifier = Modifier.fillMaxWidth(),
                        onAction = onAction
                    )
                }
            }
        }
    }
}