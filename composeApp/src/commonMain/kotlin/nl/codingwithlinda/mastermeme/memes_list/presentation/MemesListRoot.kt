package nl.codingwithlinda.mastermeme.memes_list.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import nl.codingwithlinda.mastermeme.memes_list.presentation.components.MemeListScreen

@Composable
fun MemesListRoot(modifier: Modifier = Modifier) {

    MemeListScreen(
        memes = listOf()
    )
}