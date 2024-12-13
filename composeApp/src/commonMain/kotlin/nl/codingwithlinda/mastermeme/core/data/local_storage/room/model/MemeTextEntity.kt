package nl.codingwithlinda.mastermeme.core.data.local_storage.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MemeTextEntity(
    val memeId: String,
    val imageUri: String,
    val text: String,
    val fontResource: Int,
    val fontSize: Float,
    val textColor: Int,
    val offsetX: Float,
    val offsetY: Float,
    ){
    @PrimaryKey (autoGenerate = true) var pKey: Int = 0
}
