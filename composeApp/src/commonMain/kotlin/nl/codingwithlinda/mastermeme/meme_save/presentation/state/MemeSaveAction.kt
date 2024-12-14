package nl.codingwithlinda.mastermeme.meme_save.presentation.state

sealed interface MemeSaveAction {
    data class SaveMeme(val byteArray: ByteArray) : MemeSaveAction
}