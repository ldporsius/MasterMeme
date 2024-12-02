package nl.codingwithlinda.mastermeme.meme_creator.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorAction
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorViewState

@Composable
fun MemeCreatorScreen(
    modifier: Modifier = Modifier,
   state: MemeCreatorViewState,
    onAction: (MemeCreatorAction) -> Unit
) {
    Surface (modifier = modifier){

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
            CreatorButtonsComponent(
                modifier = Modifier.fillMaxWidth(),
                onAction = onAction
            )
        }
    }

}