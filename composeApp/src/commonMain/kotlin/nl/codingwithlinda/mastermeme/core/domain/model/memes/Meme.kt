package nl.codingwithlinda.mastermeme.core.domain.model.memes

data class Meme(
    val id: String,
    val name: String,
    val image: ByteArray,
    val dateCreated: Long
)
