package nl.codingwithlinda.mastermeme.memes_list.presentation

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nl.codingwithlinda.mastermeme.memes_list.presentation.components.MemeListScreen

@Composable
fun MemesListRoot() {

    val memeListViewModel:MemeListViewModel  = viewModel()
    MemeListScreen(
        state = memeListViewModel.state.collectAsStateWithLifecycle().value,
        onAction = memeListViewModel::handleAction
    )
}