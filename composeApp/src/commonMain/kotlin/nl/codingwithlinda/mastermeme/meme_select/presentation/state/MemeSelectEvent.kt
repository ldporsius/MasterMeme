package nl.codingwithlinda.mastermeme.meme_select.presentation.state

interface MemeSelectEvent {
    object ShowDeleteMemesDialog: MemeSelectEvent
    data class ShowShareMemesDialog(val memeUris: List<String>): MemeSelectEvent
}