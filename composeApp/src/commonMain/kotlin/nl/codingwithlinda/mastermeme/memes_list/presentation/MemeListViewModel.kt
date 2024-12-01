package nl.codingwithlinda.mastermeme.memes_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import nl.codingwithlinda.mastermeme.core.presentation.MemeTemplatesImpl
import nl.codingwithlinda.mastermeme.memes_list.presentation.state.MemeListAction
import nl.codingwithlinda.mastermeme.memes_list.presentation.state.MemeListViewState

class MemeListViewModel(

): ViewModel() {

    private val _state = MutableStateFlow(MemeListViewState())
    val state = _state
        .onStart {
            _state.update {
                it.copy(templates = MemeTemplatesImpl.getTemplates())
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), _state.value)

    fun handleAction(action: MemeListAction) {
        when (action) {
            is MemeListAction.CreateNewMeme -> {
                _state.value = _state.value.copy(
                    showMemePicker = false
                )
            }

            MemeListAction.HideMemePicker -> {
                _state.value = _state.value.copy(
                    showMemePicker = false
                )
            }
            MemeListAction.ShowMemePicker -> {
                _state.value = _state.value.copy(
                    showMemePicker = true
                )

            }
        }
    }


}