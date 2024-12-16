package nl.codingwithlinda.mastermeme.core.presentation.model

data class MemeUi(
    val id: String,
    val name: String,
    val imageUri: String,
    val image: MemeImageUi,
    val dateCreated: Long,
    val isFavorite: Boolean,
)
