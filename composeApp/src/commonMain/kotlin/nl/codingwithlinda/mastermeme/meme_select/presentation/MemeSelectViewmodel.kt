package nl.codingwithlinda.mastermeme.meme_select.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch
import mastermeme.composeapp.generated.resources.Res
import mastermeme.composeapp.generated.resources.vector_18
import nl.codingwithlinda.mastermeme.core.data.local_cache.InternalStorageInteractor
import nl.codingwithlinda.mastermeme.core.data.local_storage.StorageInteractor
import nl.codingwithlinda.mastermeme.core.domain.model.memes.Meme
import nl.codingwithlinda.mastermeme.core.presentation.dto.toUi
import nl.codingwithlinda.mastermeme.core.presentation.model.MemeImageUi
import nl.codingwithlinda.mastermeme.core.presentation.model.MemeUi
import nl.codingwithlinda.mastermeme.meme_select.presentation.state.MemeSelectViewState
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.decodeToImageBitmap

class MemeSelectViewmodel(
    private val storageInteractor: StorageInteractor<Meme>,
    private val internalStorageInteractor: InternalStorageInteractor,
    private val memeId: String
): ViewModel() {

    private val savedMemes = storageInteractor.readAll()

    @OptIn(ExperimentalResourceApi::class)
    private val savedMemesToUi : Flow<List<MemeUi>> = savedMemes.transform{ memes ->
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
    private val _state = MutableStateFlow(MemeSelectViewState())
    val state = _state.asStateFlow()
        .onStart {
            val memeAlreadySelected = storageInteractor.read(memeId)

            _state.value = _state.value.copy(
                memes = savedMemesToUi.first()
            )
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), _state.value)



    private fun deleteMeme(){
        viewModelScope.launch {

        }

    }



}