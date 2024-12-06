package nl.codingwithlinda.mastermeme.memes_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import nl.codingwithlinda.mastermeme.core.domain.model.templates.MemeTemplates
import nl.codingwithlinda.mastermeme.core.presentation.templates.MemeTemplatesFromResources
import nl.codingwithlinda.mastermeme.core.presentation.templates.toUi
import nl.codingwithlinda.mastermeme.memes_list.presentation.state.MemeListAction
import nl.codingwithlinda.mastermeme.memes_list.presentation.state.MemeListViewState

class MemeListViewModel(
    memeTemplates: MemeTemplates
): ViewModel() {

    private val _state = MutableStateFlow(MemeListViewState())
    val state = _state
        .onStart {
            _state.update {
                it.copy(templates = memeTemplates.toUi())
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

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val templates = MemeTemplatesFromResources()
                MemeListViewModel(templates)
            }
        }
    }

}