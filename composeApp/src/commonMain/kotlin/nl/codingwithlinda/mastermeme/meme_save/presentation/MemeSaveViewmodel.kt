package nl.codingwithlinda.mastermeme.meme_save.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import nl.codingwithlinda.mastermeme.core.data.local_storage.StorageInteractor
import nl.codingwithlinda.mastermeme.core.domain.model.memes.Meme
import nl.codingwithlinda.mastermeme.core.presentation.share_application_picker.ImageConverter
import nl.codingwithlinda.mastermeme.meme_save.presentation.state.MemeSaveViewState

class MemeSaveViewmodel(
    private val storageInteractor: StorageInteractor<Meme>,
    private val imageConverter: ImageConverter,
    private val memeId: String
): ViewModel() {

    private val _state = MutableStateFlow(MemeSaveViewState())
    val state = _state.asStateFlow()
    fun saveMeme(){
        viewModelScope.launch {

        }

    }



}