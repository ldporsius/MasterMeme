package nl.codingwithlinda.mastermeme.meme_creator.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import nl.codingwithlinda.mastermeme.core.domain.Templates
import nl.codingwithlinda.mastermeme.core.presentation.templates.MemeTemplatesDeclaration
import nl.codingwithlinda.mastermeme.core.presentation.templates.memeTemplateToUi
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorAction
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorViewState

class MemeCreatorViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val templates: Templates
) : ViewModel() {

    private val _state = MutableStateFlow(
        MemeCreatorViewState(
        memeImageUi =  MemeTemplatesDeclaration.emptyTemplate.image,
    ))
    val state = _state.asStateFlow()


    init {
        val memeId = savedStateHandle.get<String>("memeId") ?: ""
        _state.value = _state.value.copy(
            memeImageUi = memeTemplateToUi(
                id = memeId,
                templates = templates
            ).image
        )
    }

    fun handleAction(action: MemeCreatorAction){
        when(action){
            MemeCreatorAction.AddText -> {
                _state.value = _state.value.copy(
                    isEditing = true
                )
            }
            MemeCreatorAction.SaveMeme -> {}
            MemeCreatorAction.StopEditing -> {
                _state.value = _state.value.copy(
                    isEditing = false
                )
            }
        }
    }
}