package nl.codingwithlinda.mastermeme.meme_select.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nl.codingwithlinda.mastermeme.core.data.local_storage.StorageInteractor
import nl.codingwithlinda.mastermeme.core.domain.model.memes.Meme
import nl.codingwithlinda.mastermeme.core.presentation.model.MemeUi
import nl.codingwithlinda.mastermeme.meme_select.presentation.state.MemeSelectAction
import nl.codingwithlinda.mastermeme.meme_select.presentation.state.MemeSelectEvent
import nl.codingwithlinda.mastermeme.meme_select.presentation.state.MemeSelectViewState

class MemeSelectViewmodel(
    private val storageInteractor: StorageInteractor<Meme>,
    private val savedMemes: List<MemeUi>,
): ViewModel() {

    private val _selectedMemes = MutableStateFlow<List<String>>(listOf())

    private val _events = Channel<MemeSelectEvent>()
    val events = _events

    private val _state = MutableStateFlow(MemeSelectViewState())
    val state = combine(_state, _selectedMemes) { state, selectedMemes ->
        state.copy(
            selectedMemes = selectedMemes,
        )

    }.onStart {
            _state.update {
                it.copy(
                    memes = savedMemes
                )
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), _state.value)


    fun onAction(action: MemeSelectAction) {
        when (action) {
            MemeSelectAction.WarningDeleteMemes -> warningDeleteMemes()
            MemeSelectAction.DeleteMemes -> deleteMemes()
            MemeSelectAction.ShareMemes -> shareMemes()
            is MemeSelectAction.SelectMeme -> selectMeme(action.memeId)
            MemeSelectAction.ClearSelection -> _selectedMemes.update { emptyList() }
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
                storageInteractor.delete(it)
            }
            _selectedMemes.update { emptyList() }
        }
    }



}