package local_storage.room.model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity

@Entity(
    primaryKeys = ["imageUrl", "id"]
)
data class MemeTextEntity(
    val imageUri: String,
    val id: Int,
    val text: String,
    val fontResource: Int,
    val fontSize: Float,
    val textColor: Color,
    val offsetX: Float,
    val offsetY: Float,
    val parentWidth: Float,
    val parentHeight: Float,

    )
