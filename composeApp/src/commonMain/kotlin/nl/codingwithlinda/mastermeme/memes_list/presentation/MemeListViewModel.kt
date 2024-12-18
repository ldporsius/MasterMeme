package nl.codingwithlinda.mastermeme.memes_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mastermeme.composeapp.generated.resources.Res
import mastermeme.composeapp.generated.resources.vector_18
import nl.codingwithlinda.mastermeme.core.data.local_cache.InternalStorageInteractor
import nl.codingwithlinda.mastermeme.core.domain.model.templates.MemeTemplates
import nl.codingwithlinda.mastermeme.core.domain.model.templates.templates
import nl.codingwithlinda.mastermeme.core.presentation.dto.toUi
import nl.codingwithlinda.mastermeme.core.presentation.model.MemeImageUi
import nl.codingwithlinda.mastermeme.core.presentation.model.MemeUi
import nl.codingwithlinda.mastermeme.core.presentation.templates.toUi
import nl.codingwithlinda.mastermeme.memes_list.domain.MemeListRepository
import nl.codingwithlinda.mastermeme.memes_list.presentation.home_screen.top_bar.MemeSortOption
import nl.codingwithlinda.mastermeme.memes_list.presentation.state.MemeListAction
import nl.codingwithlinda.mastermeme.memes_list.presentation.state.MemeListViewState
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.decodeToImageBitmap

class MemeListViewModel(
    memeTemplates: MemeTemplates,
    private val memeListRepository: MemeListRepository,
    private val internalStorageInteractor: InternalStorageInteractor,
    private val navToMemeCreator: (memeId: String) -> Unit,
    private val navToMemeSelect: (memeId: String, sortOption: MemeSortOption) -> Unit
): ViewModel() {

    private val savedMemes = memeListRepository.getMemes()

    @OptIn(ExperimentalResourceApi::class)
    private val savedMemesToUi : Flow<List<MemeUi>> = savedMemes.transform{memes ->
           val memesUi = memes.map { meme ->
               try {
                   val uri = meme.imageUri
                   println("MEME LIST VIEWMODEL HAS IMAGE uri: $uri")

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
    val state = _state.asStateFlow()
        .onStart {
            CoroutineScope(Dispatchers.Default).launch {
                val templates = memeTemplates.toUi()
                val memes = savedMemesToUi.first()
                withContext(viewModelScope.coroutineContext) {
                    _state.update {
                        it.copy(
                            memes = memes,
                            templates = templates,
                            isLoading = false
                        )
                    }
                }
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), _state.value)

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
                navToMemeSelect(action.id, state.value.selectedSortOption)
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

}