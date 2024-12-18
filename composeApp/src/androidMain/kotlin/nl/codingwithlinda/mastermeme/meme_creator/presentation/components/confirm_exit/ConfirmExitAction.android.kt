package nl.codingwithlinda.mastermeme.meme_creator.presentation.components.confirm_exit

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable


@Composable
actual fun BackHandler(
    enabled: Boolean,
    onBackPressed: () -> Unit
) {
    BackHandler {
        if (enabled) {
            onBackPressed()
        }
    }
}