package nl.codingwithlinda.mastermeme.meme_select.presentation.state

sealed interface MemeSaveAction {
    data class SaveMeme(val byteArray: ByteArray) : MemeSaveAction
}