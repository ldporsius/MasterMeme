package nl.codingwithlinda.mastermeme.meme_select.presentation.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import nl.codingwithlinda.mastermeme.meme_select.presentation.state.MemeSelectViewState

@Composable
fun MemeSelectScreen(
   viewState: MemeSelectViewState
) {

    AnimatedContent(viewState.memeImageUi != null){
        when(it){
            true -> {
                viewState.memeImageUi?.let { img ->

                }
            }
            false -> {
                CircularProgressIndicator()
                }
        }
    }


}