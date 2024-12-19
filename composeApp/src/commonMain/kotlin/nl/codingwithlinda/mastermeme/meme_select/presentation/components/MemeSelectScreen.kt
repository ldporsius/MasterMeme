package nl.codingwithlinda.mastermeme.meme_select.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import nl.codingwithlinda.mastermeme.core.presentation.share_application_picker.ShareAppPicker
import nl.codingwithlinda.mastermeme.meme_select.presentation.state.MemeSelectAction
import nl.codingwithlinda.mastermeme.meme_select.presentation.state.MemeSelectEvent
import nl.codingwithlinda.mastermeme.meme_select.presentation.state.MemeSelectViewState

@Composable
fun MemeSelectScreen(
    viewState: MemeSelectViewState,
    memeSelectEvent: Channel<MemeSelectEvent>,
    shareAppPicker: ShareAppPicker,
    onAction: (MemeSelectAction) -> Unit,
) {
    shareAppPicker.registerPicker {  }

    var shouldShowDeleteDialog by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(true) {
        memeSelectEvent.consumeEach {event ->
            when (event) {
                MemeSelectEvent.ShowDeleteMemesDialog -> {
                    shouldShowDeleteDialog = true
                }
                is MemeSelectEvent.ShowShareMemesDialog -> {
                    println("SHARING MEMES: ${event.memeUris}")
                    try {
                        shareAppPicker.shareMultiple(event.memeUris)
                    }
                    catch (e: Exception){
                        e.printStackTrace()
                    }
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
