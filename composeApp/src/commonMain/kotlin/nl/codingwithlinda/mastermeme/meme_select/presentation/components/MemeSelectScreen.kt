package nl.codingwithlinda.mastermeme.meme_select.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import nl.codingwithlinda.mastermeme.core.presentation.share_application_picker.ShareAppPicker
import nl.codingwithlinda.mastermeme.meme_select.presentation.state.MemeSelectAction
import nl.codingwithlinda.mastermeme.meme_select.presentation.state.MemeSelectEvent
import nl.codingwithlinda.mastermeme.meme_select.presentation.state.MemeSelectViewState

@Composable
fun MemeSelectScreen(
    numSelected: Int,
    memeSelectEvent: Flow<MemeSelectEvent>,
    shareAppPicker: ShareAppPicker,
    onAction: (MemeSelectAction) -> Unit,
) {
    shareAppPicker.registerPicker {  }

    var shouldShowDeleteDialog by remember {
        mutableStateOf(false)
    }

    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(lifecycleOwner.lifecycle) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            memeSelectEvent.collectLatest { memeSelectEvent ->
                when (memeSelectEvent) {
                    MemeSelectEvent.ShowDeleteMemesDialog -> {
                        shouldShowDeleteDialog = true
                    }

                    is MemeSelectEvent.ShowShareMemesDialog -> {
                        try {
                            shareAppPicker.shareMultiple(memeSelectEvent.memeUris)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        }
    }

    if (shouldShowDeleteDialog){

        DeleteMemesDialog(
            numberToDelete = numSelected,
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
