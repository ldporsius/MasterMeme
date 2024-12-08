package nl.codingwithlinda.mastermeme.meme_creator.presentation.components.confirm_exit

import androidx.compose.runtime.Composable

@Composable
expect fun BackHandler(
    onCancel: () -> Unit,
    onConfirm: () -> Unit
)