package nl.codingwithlinda.mastermeme.meme_select.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.room.util.TableInfo
import nl.codingwithlinda.mastermeme.meme_select.presentation.components.top_bar.MemeSelectTopBar
import nl.codingwithlinda.mastermeme.meme_select.presentation.state.MemeSelectAction
import nl.codingwithlinda.mastermeme.meme_select.presentation.state.MemeSelectViewState
import nl.codingwithlinda.mastermeme.memes_list.presentation.home_screen.MemeListAdaptiveLayout

@Composable
fun MemeSelectScreen(
    viewState: MemeSelectViewState,
    onAction: (MemeSelectAction) -> Unit,
    onBackClick: () -> Unit
) {
    Column(){
        MemeSelectTopBar(
            numSelected = viewState.selectedMemesCount,
            onAction = onAction,
            onBackClick = onBackClick
        )
        MemeSelectAdaptiveLayout(
            memes = viewState.sortedMemes,
            viewState = viewState,
            onMemeClick = {
                onAction(MemeSelectAction.SelectMeme(it))
            },
        )
    }
}
