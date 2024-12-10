package nl.codingwithlinda.mastermeme.memes_list.presentation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import local_storage.StorageInteractor
import nl.codingwithlinda.mastermeme.core.domain.local_cache.LocalCache
import nl.codingwithlinda.mastermeme.core.domain.model.memes.Meme
import nl.codingwithlinda.mastermeme.core.presentation.share_application_picker.ImageConverter
import nl.codingwithlinda.mastermeme.core.presentation.templates.MemeTemplatesFromResources
import nl.codingwithlinda.mastermeme.memes_list.presentation.components.MemeListScreen
import nl.codingwithlinda.mastermeme.memes_list.presentation.state.MemeListAction

@Composable
fun MemesListRoot(
    storageInteractor: StorageInteractor<Meme>,
    imageConverter: ImageConverter,
    navToMemeCreator: (id: String) -> Unit
) {
    val Factory: ViewModelProvider.Factory = viewModelFactory {
        initializer {
            val templates = MemeTemplatesFromResources()
            MemeListViewModel(
                memeTemplates = templates,
                imageConverter = imageConverter,
                storageInteractor = storageInteractor
            )
        }

    }
    val memeListViewModel:MemeListViewModel  = viewModel(
        factory = Factory
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