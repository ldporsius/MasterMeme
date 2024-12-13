package nl.codingwithlinda.mastermeme.core.presentation.create_meme

import androidx.compose.runtime.Composable
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorAction
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorViewState


@Composable
actual fun PictureDrawer(
    state: MemeCreatorViewState,
    ourPlatformTextStyle: OurPlatformTextStyle,
    onAction: (MemeCreatorAction) -> Unit,
    onSave: (ByteArray) -> Unit,
) {
}