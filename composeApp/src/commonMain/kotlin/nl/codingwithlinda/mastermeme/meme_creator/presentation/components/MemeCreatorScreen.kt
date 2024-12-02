package nl.codingwithlinda.mastermeme.meme_creator.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorViewState

@Composable
fun MemeCreatorScreen(
    modifier: Modifier = Modifier,
   state: MemeCreatorViewState
) {
    Surface (modifier = modifier){

        Column {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)

            ) {
                state.memeImageUi.DrawImage()
            }

            CreatorButtonsComponent(
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

}