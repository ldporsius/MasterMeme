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
import nl.codingwithlinda.mastermeme.core.presentation.share_application_picker.ImageConverter
import nl.codingwithlinda.mastermeme.core.presentation.templates.MemeTemplatesDeclaration
import nl.codingwithlinda.mastermeme.meme_creator.presentation.memento.MementoCareTaker
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorAction
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorViewState
import nl.codingwithlinda.mastermeme.meme_creator.presentation.ui_model.MemeUiText

class MemeCreatorViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val templates: Templates,
    private val imageConverter: ImageConverter
) : ViewModel() {

    private val mementoCareTakers:MutableMap<Int, MementoCareTaker<MemeUiText>> = mutableMapOf()

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
                    fontSize = 50f
                )
                _memeTexts.update {
                    it.plus(newIndex to newMemeText)
                }
                _selectedMemeIndex.update {
                    newIndex
                }
                setCurrentMemeTextEditing(newIndex)

            }

            is MemeCreatorAction.PositionText -> {
               positionText(action)
            }

            MemeCreatorAction.StopEditing -> {
                setNotCurrentMemeTextEditing()
                setNoneSelected()
            }

            is MemeCreatorAction.EditMemeText -> {
                val currentMemeText = getMemeText(action.id)

                val updateMemeText = currentMemeText.copy(
                    text = action.text
                )
                _memeTexts.update {
                    it.plus(action.id to updateMemeText)
                }
            }

            is MemeCreatorAction.DeleteMemeText -> {
                _memeTexts.update {
                    it.minus(action.index)
                }

                setNotCurrentMemeTextEditing()
                setNoneSelected()
            }

            is MemeCreatorAction.SelectMemeText -> {
                putMemeTextInHistory(action.index)

                setCurrentMemeTextEditing(action.index)

                _selectedMemeIndex.update { action.index }
            }

            is MemeCreatorAction.AdjustTextSize -> {
                val currentMemeText = getMemeText(action.id)
                val updateMemeText = currentMemeText.copy(
                    fontSize = action.size
                )
                _memeTexts.update {
                    it.plus(action.id to updateMemeText)
                }
            }
            is MemeCreatorAction.UndoTextSize -> {
                restoreFromHistory(action.id)
            }

            MemeCreatorAction.StartSaveMeme -> {
                _state.update {
                    it.copy(
                        isSaving = true
                    )
                }

                val uri = imageConverter.byteArrayToUri(ByteArray(0))

                _state.update {
                    it.copy(
                        memeUri = uri
                    )
                }
            }
            MemeCreatorAction.CancelSaveMeme -> {
                _state.update {
                    it.copy(
                        isSaving = false
                    )
                }
            }
            MemeCreatorAction.SaveMeme -> {
                _state.update {
                    it.copy(
                        isSaving = false
                    )
                }
            }
            is MemeCreatorAction.ShareMeme -> {


            }

        }
    }

    private fun getMemeText(id: Int): MemeUiText {
        return _memeTexts.value[id] ?: throw Exception("Meme text not found")
    }

    private fun setCurrentMemeTextEditing(id: Int) {
        _memeTexts.update {
            it.mapValues { entry ->
                entry.value.copy(
                    isEditing = entry.key == id
                )
            }
        }
    }
    private fun setNotCurrentMemeTextEditing() {
        _memeTexts.update {
            it.mapValues { entry ->
                entry.value.copy(
                    isEditing = false
                )
            }
        }
    }

    private fun setNoneSelected(){
        _selectedMemeIndex.update {
            -1
        }
    }

    private fun positionText(action: MemeCreatorAction.PositionText){
        val updateMemeText = getMemeText(action.id)?.copy(
            offsetX = action.offsetX,
            offsetY = action.offsetY,
            parentWidth = action.parentWidth,
            parentHeight = action.parentHeight
        ) ?: return
        _memeTexts.update {
            it.plus(action.id to updateMemeText)
        }
    }

    /* history */
    private fun getCareTakerOrNew(id: Int): MementoCareTaker<MemeUiText> {
        val caretaker = mementoCareTakers[id] ?: MementoCareTaker()
        mementoCareTakers[id] = caretaker
        return caretaker
    }
    private fun putMemeTextInHistory(id: Int) {
        getMemeText(id).also {
            getCareTakerOrNew(id).saveState(it.saveState())
        }
    }
    private fun restoreFromHistory(id: Int) {
        val currentMemeText = getMemeText(id)
        val careTaker = getCareTakerOrNew(id)

        currentMemeText.restoreState(careTaker).also {
            _memeTexts.update { memeTexts ->
                memeTexts.plus(id to it)
            }
        }
    }
}