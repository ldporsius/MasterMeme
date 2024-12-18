package nl.codingwithlinda.mastermeme.core.presentation.create_meme

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier



@Composable
actual fun PictureDrawer(
    modifier: Modifier,
    content: @Composable () -> Unit,
    onSave: (ByteArray) -> Unit,
) {
}