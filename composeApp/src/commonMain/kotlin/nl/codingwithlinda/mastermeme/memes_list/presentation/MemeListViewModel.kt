package nl.codingwithlinda.mastermeme.memes_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import mastermeme.composeapp.generated.resources.Res
import mastermeme.composeapp.generated.resources.vector_18
import nl.codingwithlinda.mastermeme.app.di.DispatcherProvider
import nl.codingwithlinda.mastermeme.core.data.local_cache.InternalStorageInteractor
import nl.codingwithlinda.mastermeme.core.domain.model.templates.MemeTemplatesProvider
import nl.codingwithlinda.mastermeme.core.presentation.dto.toUi
import nl.codingwithlinda.mastermeme.core.presentation.model.MemeImageUi
import nl.codingwithlinda.mastermeme.core.presentation.model.MemeUi
import nl.codingwithlinda.mastermeme.core.presentation.templates.toUi
import nl.codingwithlinda.mastermeme.meme_select.presentation.state.MemeSelectAction
import nl.codingwithlinda.mastermeme.meme_select.presentation.state.MemeSelectEvent
import nl.codingwithlinda.mastermeme.memes_list.domain.MemeListRepository
import nl.codingwithlinda.mastermeme.memes_list.presentation.state.MemeListAction
import nl.codingwithlinda.mastermeme.memes_list.presentation.state.MemeListInteraction
import nl.codingwithlinda.mastermeme.memes_list.presentation.state.MemeListViewState
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.decodeToImageBitmap

class MemeListViewModel(
    dispatcherProvider: DispatcherProvider,
    memeTemplatesProvider: MemeTemplatesProvider,
    private val memeListRepository: MemeListRepository,
    private val internalStorageInteractor: InternalStorageInteractor,
    private val navToMemeCreator: (memeId: String) -> Unit,
): ViewModel() {

    private val savedMemes = memeListRepository.getMemes()
    private val _selectedMemes = MutableStateFlow<List<String>>(listOf())

    @OptIn(ExperimentalResourceApi::class)
    private val savedMemesToUi : Flow<List<MemeUi>> = savedMemes.transform{memes ->
           val memesUi = memes.map { meme ->
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
        emit(memesUi)
    }

    private val _state = MutableStateFlow(MemeListViewState())
    val state = combine(_state, _selectedMemes){ state, selectedMemes ->
        state.copy(
            selectedMemes = selectedMemes,
        )
    }.onStart {
            CoroutineScope(dispatcherProvider.default).launch {
                val templates = memeTemplatesProvider.toUi()
                val memes = savedMemesToUi.first()

                    _state.update {
                        it.copy(
                            memes = memes,
                            templates = templates,
                            isLoading = false
                        )
                    }

            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), _state.value)

    private val _events = Channel<MemeSelectEvent>()
    val events = _events.receiveAsFlow()

    fun handleAction(action: MemeListAction) {
        when (action) {
            is MemeListAction.CreateNewMeme -> {
                _state.update {
                    it.copy(
                        showMemePicker = false
                    )
                }
                navToMemeCreator(action.id)
            }

            MemeListAction.HideMemePicker -> {
                _state.update {
                    it.copy(
                        searchQuery = "",
                        showMemePicker = false
                    )
                }
            }
            MemeListAction.ShowMemePicker -> {
                _state.update {
                    it.copy(
                        showMemePicker = true
                    )
                }
            }
            is MemeListAction.MemeClicked -> {
                viewModelScope.launch {
                    memeListRepository.toggleFavorite(action.id)

                    val item = state.value.memes.find { it.id == action.id } ?: return@launch
                    val update = state.value.memes.minus(item).plus(item.copy(isFavorite = !item.isFavorite))

                    _state.update {
                        it.copy(
                            memes = update
                        )
                    }
                }
            }

            is MemeListAction.MemeLongPressed -> {
               println("LONG PRESSED: ${action.id}")

                _state.update {
                    it.copy(
                        interaction = MemeListInteraction.SELECTING
                    )
                }
                _selectedMemes.update {
                    it.plus(action.id)
                }
            }

            is MemeListAction.SortMemes -> {
                _state.update {
                    it.copy(
                        selectedSortOption = action.sortOption
                    )
                }
            }
            is MemeListAction.SearchTemplates -> {
                _state.update {
                    it.copy(
                       searchQuery = action.query
                    )
                }
            }
        }
    }

    fun onAction(action: MemeSelectAction) {
        when (action) {
            MemeSelectAction.WarningDeleteMemes -> warningDeleteMemes()
            MemeSelectAction.DeleteMemes -> deleteMemes()
            MemeSelectAction.ShareMemes -> shareMemes()
            is MemeSelectAction.SelectMeme -> selectMeme(action.memeId)
            MemeSelectAction.StopSelection -> stopSelection()
        }
    }
    private fun selectMeme(memeId: String) {
        val isSelected = memeId in _selectedMemes.value
        if (isSelected){
            _selectedMemes.update {
                it.minus(memeId)
            }
        }else{
            _selectedMemes.update {
                it.plus(memeId)
            }
        }
    }

    private fun shareMemes() {
        viewModelScope.launch {
            _events.send(MemeSelectEvent.ShowShareMemesDialog(state.value.memeUris()))
        }
    }

    private fun warningDeleteMemes() {
        viewModelScope.launch {
            _events.send(MemeSelectEvent.ShowDeleteMemesDialog)
        }
    }
    private fun deleteMemes(){
        viewModelScope.launch {
            val toDelete = _selectedMemes.value
            println("DELETING MEMES: $toDelete")
            _state.update { viewState ->
                viewState.copy(
                    memes = viewState.memes.filterNot { it.id in toDelete },
                    selectedMemes = emptyList()
                )
            }
            toDelete.onEach {
                memeListRepository.deleteMeme(it)
            }
            _selectedMemes.update { emptyList() }
        }
    }

    private fun stopSelection(){
        _selectedMemes.update { emptyList() }
        _state.update {
            it.copy(
                interaction = MemeListInteraction.SORTING
            )
        }
    }
}