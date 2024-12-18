package nl.codingwithlinda.mastermeme.memes_list.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import mastermeme.composeapp.generated.resources.Res
import mastermeme.composeapp.generated.resources.vector_18
import nl.codingwithlinda.mastermeme.core.data.local_cache.InternalStorageInteractor
import nl.codingwithlinda.mastermeme.core.data.local_storage.StorageInteractor
import nl.codingwithlinda.mastermeme.core.domain.model.memes.Meme
import nl.codingwithlinda.mastermeme.core.presentation.dto.toUi
import nl.codingwithlinda.mastermeme.core.presentation.model.MemeImageUi
import nl.codingwithlinda.mastermeme.core.presentation.share_application_picker.ImageConverter
import nl.codingwithlinda.mastermeme.core.presentation.share_application_picker.ShareAppPicker
import nl.codingwithlinda.mastermeme.core.presentation.templates.MemeTemplatesFromResources
import nl.codingwithlinda.mastermeme.meme_select.presentation.MemeSelectRoot
import nl.codingwithlinda.mastermeme.meme_select.presentation.MemeSelectViewmodel
import nl.codingwithlinda.mastermeme.meme_select.presentation.components.MemeSelectScreen
import nl.codingwithlinda.mastermeme.meme_select.presentation.components.top_bar.MemeSelectTopBar
import nl.codingwithlinda.mastermeme.meme_select.presentation.state.MemeSelectAction
import nl.codingwithlinda.mastermeme.memes_list.data.MemeListRepoImpl
import nl.codingwithlinda.mastermeme.memes_list.presentation.home_screen.MemeListScreen
import nl.codingwithlinda.mastermeme.memes_list.presentation.home_screen.top_bar.MemeSortOption
import nl.codingwithlinda.mastermeme.memes_list.presentation.home_screen.top_bar.SortMemesTopBar
import nl.codingwithlinda.mastermeme.memes_list.presentation.state.MemeListAction
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.decodeToImageBitmap

@OptIn(ExperimentalResourceApi::class)
@Composable
fun MemesListRoot(
    storageInteractor: StorageInteractor<Meme>,
    internalStorageInteractor: InternalStorageInteractor,
    shareAppPicker: ShareAppPicker,
    navToMemeCreator: (id: String) -> Unit,
) {

    val  repo = MemeListRepoImpl(storageInteractor)


    val savedMemes = repo.getMemes().map {
        it.map {meme ->
            try {
                val uri = meme.imageUri
                val image = internalStorageInteractor.read(uri).decodeToImageBitmap()
                val imageUi = MemeImageUi.bitmapImage(image)
                meme.toUi(imageUi)
            } catch (e: Exception) {
                e.printStackTrace()
                val image = MemeImageUi.vectorImage(Res.drawable.vector_18)
                meme.toUi(image)
            }
        }
    }.stateIn(CoroutineScope(Dispatchers.Default), SharingStarted.WhileSubscribed(), emptyList())

    var isSelecting by remember {
        mutableStateOf(false)
    }
    val memeSelectFactory: ViewModelProvider.Factory = viewModelFactory {
        initializer {
            MemeSelectViewmodel(
                storageInteractor = storageInteractor,
                savedMemes = savedMemes.value,
            )
        }
    }
    val memeSelectViewModel = viewModel<MemeSelectViewmodel>(
        factory = memeSelectFactory
    )
    val Factory: ViewModelProvider.Factory = viewModelFactory {
        initializer {
            val templates = MemeTemplatesFromResources()
            MemeListViewModel(
                memeTemplates = templates,
                memeListRepository = repo,
                internalStorageInteractor = internalStorageInteractor,
                navToMemeCreator = navToMemeCreator,
                navToMemeSelect = {memeId, sortOption ->
                    isSelecting = true

                    memeSelectViewModel.onAction(MemeSelectAction.SelectMeme(memeId))
                }
            )
        }
    }
    val memeListViewModel:MemeListViewModel  = viewModel(
        factory = Factory
    )


    val listState = memeListViewModel.state.collectAsStateWithLifecycle().value
    val selectState = memeSelectViewModel.state.collectAsStateWithLifecycle().value

    Scaffold {
        Column {
            AnimatedContent(
                targetState = isSelecting
            ) { selecting ->
                if (selecting) {
                    MemeSelectTopBar(
                        numSelected = selectState.selectedMemesCount,
                        onAction = memeSelectViewModel::onAction,
                        onBackClick = {
                            isSelecting = false
                            memeSelectViewModel.onAction(MemeSelectAction.ClearSelection)
                        }
                    )
                } else {
                    SortMemesTopBar(
                        sortOptions = listState.sortOptions,
                        selectedSortOption = listState.selectedSortOption,
                        selectSortOption = {
                            memeListViewModel.handleAction(MemeListAction.SortMemes(it))
                        }
                    )
                }
            }

            MemeListScreen(
                state = memeListViewModel.state.collectAsStateWithLifecycle().value,
                isSelecting = isSelecting,
                isSelected = {
                    selectState.isSelected(it)
                },
                toggleMemeSelection = {
                    memeSelectViewModel.onAction(MemeSelectAction.SelectMeme(it))
                },
                onAction = memeListViewModel::handleAction
            )

            MemeSelectScreen(
                viewState = selectState,
                memeSelectEvent = memeSelectViewModel.events,
                shareAppPicker = shareAppPicker,
                onAction = memeSelectViewModel::onAction
            )
        }
    }


}