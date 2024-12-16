package nl.codingwithlinda.mastermeme.meme_select.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import nl.codingwithlinda.mastermeme.core.data.local_cache.InternalStorageInteractor
import nl.codingwithlinda.mastermeme.core.data.local_storage.StorageInteractor
import nl.codingwithlinda.mastermeme.core.domain.model.memes.Meme
import nl.codingwithlinda.mastermeme.core.presentation.share_application_picker.ImageConverter
import nl.codingwithlinda.mastermeme.meme_select.presentation.components.MemeSelectScreen

@Composable
fun MemeSelectRoot(
    memeId: String,
    internalStorageInteractor: InternalStorageInteractor,
    storageInteractor: StorageInteractor<Meme>,
    onBackNav: () -> Unit
) {

    val MemeSelectViewModelFactory : ViewModelProvider.Factory = viewModelFactory {
       initializer {
           MemeSelectViewmodel(
               storageInteractor = storageInteractor,
               internalStorageInteractor = internalStorageInteractor,
               memeId = memeId
           )
       }
    }
    val viewModel = viewModel<MemeSelectViewmodel>(
        factory = MemeSelectViewModelFactory
    )

    Scaffold {

        MemeSelectScreen(
            viewState = viewModel.state.collectAsStateWithLifecycle().value,
            onAction = viewModel::onAction,
            onBackClick = {
                onBackNav()
            }
        )
    }

}