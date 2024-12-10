package nl.codingwithlinda.mastermeme.core.presentation.share_application_picker

import androidx.compose.ui.graphics.ImageBitmap
import nl.codingwithlinda.mastermeme.core.data.dto.MemeDto
import nl.codingwithlinda.mastermeme.core.presentation.model.MemeImageUi


expect class ImageConverter {

    fun imageWidth(imageBitmap: ImageBitmap): Float
    fun memeDtoToUi(memeDto: MemeDto): MemeImageUi
    fun convert(memeDto: MemeDto): String

}