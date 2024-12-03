package nl.codingwithlinda.mastermeme.meme_creator.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import nl.codingwithlinda.mastermeme.core.domain.Templates
import nl.codingwithlinda.mastermeme.core.presentation.MemeImageUi
import nl.codingwithlinda.mastermeme.core.presentation.templates.MemeTemplatesDeclaration
import nl.codingwithlinda.mastermeme.core.presentation.templates.memeTemplateToUi
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorAction
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorViewState

class MemeCreatorViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val templates: Templates
) : ViewModel() {

    private val _memeTexts = MutableStateFlow<List<String>>(emptyList())
    private val _state = MutableStateFlow(
        MemeCreatorViewState(
        memeImageUi =  MemeTemplatesDeclaration.emptyTemplate.image,
    ))
    val state = combine(_state, _memeTexts){
        state, memeTexts ->
        state.copy(
            memeTexts = memeTexts
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _state.value)


    init {
        val memeId = savedStateHandle.get<String>("memeId") ?: ""
        _state.value = _state.value.copy(
            memeImageUi = MemeImageUi.pngImage(
                templates.getTemplate(memeId).drawableResource
            )
        )
    }

    fun handleAction(action: MemeCreatorAction){
        when(action){
            MemeCreatorAction.AddText -> {
                _memeTexts.update {
                    it + ""
                }
                _state.update {
                    it.copy(
                        memeTexts = _memeTexts.value,

                    )
                }
            }
            MemeCreatorAction.SaveMeme -> {}

            MemeCreatorAction.StartEditing -> {
                _state.update {
                    it.copy(
                        isEditing = true
                    )
                }
            }
            MemeCreatorAction.StopEditing -> {
                _memeTexts.update {
                    it.dropLast(1)
                }
                _state.update {
                    it.copy(
                        isEditing = false
                    )
                }
            }
            is MemeCreatorAction.EditMemeText -> {
                val currentMemeTextIndex = _state.value.selectedMemeTextIndex

                _memeTexts.value.toMutableList().add(currentMemeTextIndex, action.text)

                _state.update {
                    it.copy(
                        memeTexts = _memeTexts.value
                    )
                }
            }

            is MemeCreatorAction.SelectMemeText -> {
                _state.update {
                    it.copy(
                        selectedMemeTextIndex = action.index ,
                        //isEditing = true
                    )
                }
            }
        }
    }
}