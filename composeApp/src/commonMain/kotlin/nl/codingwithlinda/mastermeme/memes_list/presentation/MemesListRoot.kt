package nl.codingwithlinda.mastermeme.memes_list.presentation

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nl.codingwithlinda.mastermeme.memes_list.presentation.components.MemeListScreen
import nl.codingwithlinda.mastermeme.memes_list.presentation.state.MemeListAction

@Composable
fun MemesListRoot(
    navToMemeCreator: (id: String) -> Unit
) {

    val memeListViewModel:MemeListViewModel  = viewModel(
        factory = MemeListViewModel.Factory
    )
    MemeListScreen(
        state = memeListViewModel.state.collectAsStateWithLifecycle().value,
        onAction = {
            if(it is MemeListAction.CreateNewMeme){
                navToMemeCreator(it.id)
            }else{
                memeListViewModel.handleAction(it)
            }
        }
    )
}