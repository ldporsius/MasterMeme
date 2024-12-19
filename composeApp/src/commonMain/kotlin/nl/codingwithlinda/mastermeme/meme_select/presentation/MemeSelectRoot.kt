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
import nl.codingwithlinda.mastermeme.core.presentation.share_application_picker.ShareAppPicker
import nl.codingwithlinda.mastermeme.meme_select.presentation.components.MemeSelectScreen
import nl.codingwithlinda.mastermeme.memes_list.presentation.home_screen.top_bar.MemeSortOption

@Composable
fun MemeSelectRoot(
    memeId: String,
    sortOption: MemeSortOption,
    internalStorageInteractor: InternalStorageInteractor,
    storageInteractor: StorageInteractor<Meme>,
    shareAppPicker: ShareAppPicker,
    onBackNav: () -> Unit
) {

    val MemeSelectViewModelFactory : ViewModelProvider.Factory = viewModelFactory {
       initializer {
           MemeSelectViewmodel(
               storageInteractor = storageInteractor,
               internalStorageInteractor = internalStorageInteractor,
               memeId = memeId,
               sortOption = sortOption
           )
       }
    }
    val viewModel = viewModel<MemeSelectViewmodel>(
        factory = MemeSelectViewModelFactory
    )

    Scaffold {

        MemeSelectScreen(
            viewState = viewModel.state.collectAsStateWithLifecycle().value,
            shareAppPicker = shareAppPicker,
            onAction = viewModel::onAction,
            memeSelectEvent = viewModel.events,
        )
    }

}