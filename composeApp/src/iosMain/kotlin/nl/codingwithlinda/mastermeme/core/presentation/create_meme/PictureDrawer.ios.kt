package nl.codingwithlinda.mastermeme.core.presentation.create_meme

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineDispatcher


@Composable
actual fun PictureDrawer(
    dispatcher: CoroutineDispatcher,
    modifier: Modifier,
    content: @Composable () -> Unit,
    onSave: (ByteArray) -> Unit,
) {
}