package nl.codingwithlinda.mastermeme.meme_select.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import mastermeme.composeapp.generated.resources.Res
import mastermeme.composeapp.generated.resources.vector_18
import nl.codingwithlinda.mastermeme.core.data.local_cache.InternalStorageInteractor
import nl.codingwithlinda.mastermeme.core.data.local_storage.StorageInteractor
import nl.codingwithlinda.mastermeme.core.domain.model.memes.Meme
import nl.codingwithlinda.mastermeme.core.presentation.dto.toUi
import nl.codingwithlinda.mastermeme.core.presentation.model.MemeImageUi
import nl.codingwithlinda.mastermeme.core.presentation.model.MemeUi
import nl.codingwithlinda.mastermeme.meme_select.presentation.state.MemeSelectAction
import nl.codingwithlinda.mastermeme.meme_select.presentation.state.MemeSelectViewState
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.decodeToImageBitmap

class MemeSelectViewmodel(
    private val storageInteractor: StorageInteractor<Meme>,
    private val internalStorageInteractor: InternalStorageInteractor,
    private val memeId: String
): ViewModel() {

    private val savedMemes = storageInteractor.readAll()
    private val _selectedMemes = MutableStateFlow<List<String>>(listOf(memeId))

    @OptIn(ExperimentalResourceApi::class)
    private val savedMemesToUi : Flow<List<MemeUi>> = savedMemes.transform{ memes ->
        val memesUi = memes.map { meme ->
            try {
                val uri = meme.imageUri

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
    val state = combine(_state, _selectedMemes) { state, selectedMemes ->
        state.copy(
            selectedMemes = selectedMemes
        )

    }.onStart {
            _state.update {
                it.copy(
                    memes = savedMemesToUi.first()
                )
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), _state.value)


    fun onAction(action: MemeSelectAction) {
        when (action) {
            MemeSelectAction.DeleteMemes -> deleteMeme()
            MemeSelectAction.ShareMemes -> shareMemes()
            is MemeSelectAction.SelectMeme -> selectMeme(action.memeId)
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
        }
        }
    private fun deleteMeme(){
        viewModelScope.launch {

        }

    }



}