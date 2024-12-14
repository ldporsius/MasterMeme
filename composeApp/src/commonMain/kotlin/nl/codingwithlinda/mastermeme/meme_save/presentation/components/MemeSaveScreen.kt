package nl.codingwithlinda.mastermeme.meme_save.presentation.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import nl.codingwithlinda.mastermeme.meme_save.presentation.state.MemeSaveViewState

@Composable
fun MemeSaveScreen(
   viewState: MemeSaveViewState
) {

    AnimatedContent(viewState.memeImageUi != null){
        when(it){
            true -> {
                viewState.memeImageUi?.let { img ->
                    PictureDrawerImpl(
                        memeImageUi = img,
                        memeTexts = viewState.memeTexts,
                        onSave = {

                        }
                    )
                }
            }
            false -> {
                CircularProgressIndicator()
                }
        }
    }


}