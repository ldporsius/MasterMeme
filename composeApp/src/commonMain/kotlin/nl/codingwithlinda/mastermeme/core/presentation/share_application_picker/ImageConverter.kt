package nl.codingwithlinda.mastermeme.core.presentation.share_application_picker

import nl.codingwithlinda.mastermeme.core.data.dto.MemeDto
import nl.codingwithlinda.mastermeme.core.presentation.model.MemeImageUi


expect class ImageConverter {

    suspend fun memeDtoToUi(memeDto: MemeDto): MemeImageUi
    suspend fun convert(memeDto: MemeDto): String
    fun share(bytes: ByteArray): String


}