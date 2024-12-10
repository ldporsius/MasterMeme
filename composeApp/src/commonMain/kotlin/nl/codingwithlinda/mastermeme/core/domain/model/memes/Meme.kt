package nl.codingwithlinda.mastermeme.core.domain.model.memes

data class Meme(
    val id: String,
    val name: String,
    val imageUri: String,
    val dateCreated: Long,
    val isFavorite: Boolean,
    val texts: List<MemeText>

)
