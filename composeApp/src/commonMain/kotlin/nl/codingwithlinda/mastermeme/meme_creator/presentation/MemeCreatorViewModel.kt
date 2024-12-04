package nl.codingwithlinda.mastermeme.meme_creator.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import nl.codingwithlinda.mastermeme.core.domain.Templates
import nl.codingwithlinda.mastermeme.core.presentation.MemeImageUi
import nl.codingwithlinda.mastermeme.core.presentation.templates.MemeTemplatesDeclaration
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorAction
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorViewState
import nl.codingwithlinda.mastermeme.meme_creator.presentation.ui_model.MemeUiText

class MemeCreatorViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val templates: Templates
) : ViewModel() {

    private val _memeTexts = MutableStateFlow<Map<Int, MemeUiText>>(emptyMap())
    private val _selectedMemeIndex = MutableStateFlow<Int>(-1)
    private val _state = MutableStateFlow(
        MemeCreatorViewState(
        memeImageUi =  MemeTemplatesDeclaration.emptyTemplate.image,
    ))
    val state = combine(_state, _memeTexts, _selectedMemeIndex){ state, memeTexts , selectedMemeIndex ->
        state.copy(
            memeTexts = memeTexts,
            selectedMemeTextIndex = selectedMemeIndex,
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
            is MemeCreatorAction.AddText -> {
                val newIndex = _memeTexts.value.keys.maxOrNull()?.plus(1) ?: 0
                val newMemeText =  MemeUiText(
                    id = newIndex,
                    text = "",
                    offsetX = 0f,
                    offsetY = 0f,
                    parentWidth = 0f,
                    parentHeight = 0f,
                    isEditing = true
                )
                _memeTexts.update {
                    it.mapValues {
                        it.value.copy(
                            isEditing = false
                        )
                    }
                }

               _memeTexts.update {
                   it.plus(newIndex to newMemeText)
                }
                _selectedMemeIndex.update {
                   newIndex
                }

            }

            is MemeCreatorAction.PositionText -> {
                val memeIndex = _selectedMemeIndex.value
                val updateMemeText = _memeTexts.value[memeIndex]?.copy(
                    offsetX = action.offsetX,
                    offsetY = action.offsetY,
                    parentWidth = action.parentWidth,
                    parentHeight = action.parentHeight
                ) ?: return
                _memeTexts.update {
                    it.plus(memeIndex to updateMemeText)
                }
            }
            MemeCreatorAction.SaveMeme -> {}

            is MemeCreatorAction.StartEditing -> {

                _selectedMemeIndex.update {
                    action.index
                }
               _state.update {
                   it.copy(
                       isEditing = true
                   )
               }
            }
            MemeCreatorAction.StopEditing -> {
                _state.update {
                    it.copy(
                        isEditing = false
                    )
                }
                _selectedMemeIndex.update {
                    -1
                }
                _memeTexts.update {
                    it.mapValues { entry ->
                        entry.value.copy(
                            isEditing = false
                        )
                    }
                }
            }
            is MemeCreatorAction.EditMemeText -> {
                val currentMemeTextIndex = _selectedMemeIndex.value
                val currentMemeText = _memeTexts.value[currentMemeTextIndex] ?: throw Exception("Meme text not found")

                val updateMemeText = currentMemeText.copy(
                        text = action.text
                    )
               _memeTexts.update {
                   it.plus(currentMemeTextIndex to updateMemeText)
                }

            }

            is MemeCreatorAction.DeleteMemeText -> {
                _memeTexts.update {
                    it.minus(action.index)
                }
                _selectedMemeIndex.update {
                    -1
                }
            }

            is MemeCreatorAction.SelectMemeText -> {
                _memeTexts.update {
                    it.mapValues { entry ->
                        entry.value.copy(
                            isEditing = entry.key == action.index
                        )
                    }
                }
                _selectedMemeIndex.update { action.index }
            }
        }
    }
}