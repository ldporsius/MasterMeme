package nl.codingwithlinda.mastermeme.meme_creator.presentation.components.confirm_exit

import androidx.compose.runtime.Composable

@Composable
expect fun BackHandler(
  enabled: Boolean = true,
    onBackPressed: () -> Unit
)