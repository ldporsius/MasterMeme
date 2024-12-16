package nl.codingwithlinda.mastermeme.meme_select.presentation.state

import nl.codingwithlinda.mastermeme.core.presentation.model.MemeUi

data class MemeSelectViewState(
    val memes: List<MemeUi> = emptyList(),
    val selectedMemes: List<String> = emptyList()
    ){

    val sortedMemes = memes
    val selectedMemesCount: Int = selectedMemes.size

    fun isSelected(memeId: String): Boolean {
        return selectedMemes.contains(memeId)
    }
}
