package nl.codingwithlinda.mastermeme.memes_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import nl.codingwithlinda.mastermeme.core.data.local_storage.StorageInteractor
import mastermeme.composeapp.generated.resources.Res
import mastermeme.composeapp.generated.resources.vector_18
import nl.codingwithlinda.mastermeme.core.data.dto.MemeDto
import nl.codingwithlinda.mastermeme.core.data.local_cache.InternalStorageInteractor
import nl.codingwithlinda.mastermeme.core.domain.model.memes.Meme
import nl.codingwithlinda.mastermeme.core.domain.model.templates.MemeTemplates
import nl.codingwithlinda.mastermeme.core.domain.model.templates.templateToBytes
import nl.codingwithlinda.mastermeme.core.presentation.dto.toUi
import nl.codingwithlinda.mastermeme.core.presentation.model.MemeImageUi
import nl.codingwithlinda.mastermeme.core.presentation.model.MemeUi
import nl.codingwithlinda.mastermeme.core.presentation.share_application_picker.ImageConverter
import nl.codingwithlinda.mastermeme.core.presentation.templates.toUi
import nl.codingwithlinda.mastermeme.memes_list.presentation.state.MemeListAction
import nl.codingwithlinda.mastermeme.memes_list.presentation.state.MemeListViewState
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.decodeToImageBitmap

class MemeListViewModel(
    memeTemplates: MemeTemplates,
    imageConverter: ImageConverter,
    private val storageInteractor: StorageInteractor<Meme>,
    private val internalStorageInteractor: InternalStorageInteractor
): ViewModel() {

    private val savedMemes = storageInteractor.readAll()

    private val _state = MutableStateFlow(MemeListViewState())
    @OptIn(ExperimentalResourceApi::class)
    val state = combine(_state, savedMemes){ state, savedMemes ->
        val memes =  savedMemes.map {meme ->
           val memeUI =  try {

               val uri = meme.imageUri
               println("MEME LIST VIEWMODEL HAS IMAGE uri: $uri")

                val image = internalStorageInteractor.read(uri).decodeToImageBitmap()

                val imageUi =  MemeImageUi.bitmapImage(image)
                meme.toUi(imageUi)
            }catch (e: Exception){
                e.printStackTrace()
                val image = MemeImageUi.vectorImage(Res.drawable.vector_18)
                MemeUi(
                    id = meme.id,
                    name = meme.name,
                    image = image,
                    dateCreated = meme.dateCreated,
                )
            }

            memeUI
        }
        state.copy(
            memes = memes
        )
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