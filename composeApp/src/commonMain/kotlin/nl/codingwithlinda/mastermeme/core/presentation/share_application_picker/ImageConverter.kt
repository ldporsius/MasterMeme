package nl.codingwithlinda.mastermeme.core.presentation.share_application_picker

import nl.codingwithlinda.mastermeme.core.data.dto.MemeDto
import nl.codingwithlinda.mastermeme.core.presentation.model.MemeImageUi


expect class ImageConverter {


    fun share(bytes: ByteArray, name: String): String


}