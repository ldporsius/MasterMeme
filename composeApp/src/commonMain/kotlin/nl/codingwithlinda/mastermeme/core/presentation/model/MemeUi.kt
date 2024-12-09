package nl.codingwithlinda.mastermeme.core.presentation.model

data class MemeUi(
    val id: String,
    val name: String,
    val image: MemeImageUi,
    val dateCreated: Long
)
