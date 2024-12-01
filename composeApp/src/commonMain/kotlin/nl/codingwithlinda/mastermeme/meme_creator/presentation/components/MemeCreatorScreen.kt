package nl.codingwithlinda.mastermeme.meme_creator.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorViewState

@Composable
fun MemeCreatorScreen(
    modifier: Modifier = Modifier,
   state: MemeCreatorViewState
) {
    Box(modifier = modifier){
        state.memeImageUi.DrawImage()
    }

}