package nl.codingwithlinda.mastermeme.memes_list.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import nl.codingwithlinda.mastermeme.app.di.DispatcherProvider
import nl.codingwithlinda.mastermeme.core.data.local_cache.InternalStorageInteractor
import nl.codingwithlinda.mastermeme.core.data.local_storage.StorageInteractor
import nl.codingwithlinda.mastermeme.core.domain.model.memes.Meme
import nl.codingwithlinda.mastermeme.core.presentation.share_application_picker.ShareAppPicker
import nl.codingwithlinda.mastermeme.core.presentation.templates.MemeTemplatesFromResources
import nl.codingwithlinda.mastermeme.meme_select.presentation.components.MemeSelectScreen
import nl.codingwithlinda.mastermeme.meme_select.presentation.components.top_bar.MemeSelectTopBar
import nl.codingwithlinda.mastermeme.meme_select.presentation.state.MemeSelectAction
import nl.codingwithlinda.mastermeme.meme_select.presentation.state.MemeSelectEvent
import nl.codingwithlinda.mastermeme.memes_list.data.MemeListRepoImpl
import nl.codingwithlinda.mastermeme.memes_list.presentation.home_screen.MemeListScreen
import nl.codingwithlinda.mastermeme.memes_list.presentation.home_screen.top_bar.SortMemesTopBar
import nl.codingwithlinda.mastermeme.memes_list.presentation.state.MemeListAction
import nl.codingwithlinda.mastermeme.memes_list.presentation.state.MemeListInteraction

@Composable
fun MemesListRoot(
    dispatcherProvider: DispatcherProvider,
    storageInteractor: StorageInteractor<Meme>,
    internalStorageInteractor: InternalStorageInteractor,
    shareAppPicker: ShareAppPicker,
    navToMemeCreator: (id: String) -> Unit,
) {

    val  repo = MemeListRepoImpl(storageInteractor)


    val Factory: ViewModelProvider.Factory = viewModelFactory {
        initializer {
            val templates = MemeTemplatesFromResources()
            MemeListViewModel(
                dispatcherProvider = dispatcherProvider,
                memeTemplatesProvider = templates,
                memeListRepository = repo,
                internalStorageInteractor = internalStorageInteractor,
                navToMemeCreator = {
                    navToMemeCreator(it)
                },
            )
        }
    }
    val memeListViewModel:MemeListViewModel  = viewModel(
        factory = Factory
    )


    val listState = memeListViewModel.state.collectAsStateWithLifecycle().value

    Scaffold {
        Column {
            AnimatedContent(
                targetState = listState.interaction
            ) { interaction ->
                when (interaction) {
                    MemeListInteraction.SORTING -> {
                        SortMemesTopBar(
                            sortOptions = listState.sortOptions,
                            selectedSortOption = listState.selectedSortOption,
                            selectSortOption = {
                                memeListViewModel.handleAction(MemeListAction.SortMemes(it))
                            }
                        )
                    }
                    MemeListInteraction.SELECTING -> {
                        MemeSelectTopBar(
                            numSelected = listState.selectedMemesCount,
                            onAction = memeListViewModel::onAction,
                            onBackClick = {
                               memeListViewModel.onAction(MemeSelectAction.StopSelection)
                            }
                        )
                    }
                }
            }

            MemeListScreen(
                state = listState,
                isSelecting = listState.interaction == MemeListInteraction.SELECTING,
                isSelected = {
                    listState.isSelected(it)
                },
                toggleMemeSelection = {
                    memeListViewModel.onAction(MemeSelectAction.SelectMeme(it))
                },
                onAction = memeListViewModel::handleAction
            )

                MemeSelectScreen(
                    numSelected = listState.selectedMemesCount,
                    memeSelectEvent = memeListViewModel.events,
                    shareAppPicker = shareAppPicker,
                    onAction = memeListViewModel::onAction
                )
            }

    }
}
