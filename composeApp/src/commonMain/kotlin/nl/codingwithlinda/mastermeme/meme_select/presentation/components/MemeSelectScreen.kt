package nl.codingwithlinda.mastermeme.meme_select.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import nl.codingwithlinda.mastermeme.meme_select.presentation.components.top_bar.MemeSelectTopBar
import nl.codingwithlinda.mastermeme.meme_select.presentation.state.MemeSelectAction
import nl.codingwithlinda.mastermeme.meme_select.presentation.state.MemeSelectEvent
import nl.codingwithlinda.mastermeme.meme_select.presentation.state.MemeSelectViewState

@Composable
fun MemeSelectScreen(
    viewState: MemeSelectViewState,
    memeSelectEvent: Channel<MemeSelectEvent>,
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

    var shouldShowDeleteDialog by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(true) {
        memeSelectEvent.consumeEach {event ->
            when (event) {
                MemeSelectEvent.ShowDeleteMemesDialog -> {
                    shouldShowDeleteDialog = true
                }
            }
        }
    }

    if (shouldShowDeleteDialog){

        DeleteMemesDialog(
            numberToDelete = viewState.selectedMemesCount,
            onConfirm = {
                onAction(MemeSelectAction.DeleteMemes)
                shouldShowDeleteDialog = false
            },
            onDismiss = {
                shouldShowDeleteDialog = false
            }
        )
    }

}
