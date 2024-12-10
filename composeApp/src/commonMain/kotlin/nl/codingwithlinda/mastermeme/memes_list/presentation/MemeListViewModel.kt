package nl.codingwithlinda.mastermeme.memes_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import local_storage.StorageInteractor
import local_storage.room.RoomStorageInteractor
import local_storage.room.model.MemeEntity
import nl.codingwithlinda.mastermeme.core.data.dto.MemeDto
import nl.codingwithlinda.mastermeme.core.domain.local_cache.LocalCache
import nl.codingwithlinda.mastermeme.core.domain.model.memes.Meme
import nl.codingwithlinda.mastermeme.core.domain.model.templates.MemeTemplates
import nl.codingwithlinda.mastermeme.core.domain.model.templates.templateToBytes
import nl.codingwithlinda.mastermeme.core.presentation.dto.toUi
import nl.codingwithlinda.mastermeme.core.presentation.model.MemeImageUi
import nl.codingwithlinda.mastermeme.core.presentation.share_application_picker.ImageConverter
import nl.codingwithlinda.mastermeme.core.presentation.templates.MemeTemplatesFromResources
import nl.codingwithlinda.mastermeme.core.presentation.templates.toUi
import nl.codingwithlinda.mastermeme.memes_list.presentation.state.MemeListAction
import nl.codingwithlinda.mastermeme.memes_list.presentation.state.MemeListViewState
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.decodeToImageBitmap

class MemeListViewModel(
    memeTemplates: MemeTemplates,
    imageConverter: ImageConverter,
    private val storageInteractor: StorageInteractor<Meme>,
): ViewModel() {

    private val savedMemes = storageInteractor.readAll()

    private val _state = MutableStateFlow(MemeListViewState())
    @OptIn(ExperimentalResourceApi::class)
    val state = combine(_state, savedMemes){ state, savedMemes ->
        state.copy(memes = savedMemes.map {
            val template = memeTemplates.getTemplate(it.id)
            val bytes = templateToBytes(template.drawableResource)

            val image = bytes.decodeToImageBitmap()

            val dto = MemeDto(
                imageBytes = bytes,
                memeTexts = it.texts,
                parentWidth = image.width.toFloat(),
                parentHeight = image.height.toFloat()
            )
            val imageUi = imageConverter.memeDtoToUi(dto)
            it.toUi(imageUi)
        })
    }
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

}