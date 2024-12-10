package nl.codingwithlinda.mastermeme.core.data.local_storage.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MemeEntity(
    @PrimaryKey val id: String,
    val name: String,
    val imageUri: String,
    val dateCreated: Long,
    val isFavorite: Boolean
)
