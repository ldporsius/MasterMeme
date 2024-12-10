package local_storage.room.model

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
    val parentWidth: Float,
    val parentHeight: Float,

    ){
    @PrimaryKey (autoGenerate = true) var primaryKey: Int = 0
}
