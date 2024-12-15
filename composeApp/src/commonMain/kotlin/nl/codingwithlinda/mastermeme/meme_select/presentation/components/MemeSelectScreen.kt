package nl.codingwithlinda.mastermeme.meme_select.presentation.components

import androidx.compose.runtime.Composable
import nl.codingwithlinda.mastermeme.meme_select.presentation.state.MemeSelectViewState
import nl.codingwithlinda.mastermeme.memes_list.presentation.home_screen.MemeListAdaptiveLayout

@Composable
fun MemeSelectScreen(
   viewState: MemeSelectViewState
) {

   MemeSelectAdaptiveLayout(
       memes = viewState.memes,
       onMemeClick = {},
       onMemeLongPress = {}
   )

}