package nl.codingwithlinda.mastermeme.core.presentation.share_application_picker

import nl.codingwithlinda.mastermeme.core.data.dto.MemeDto


expect class ImageConverter {

    fun convert(memeDto: MemeDto): String

}