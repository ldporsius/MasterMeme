package nl.codingwithlinda.mastermeme.core.presentation.create_meme

import androidx.compose.runtime.Composable
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorAction
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorViewState

@Composable
expect fun PictureDrawer(
    content: @Composable () -> Unit,
    onSave: (ByteArray) -> Unit,
)