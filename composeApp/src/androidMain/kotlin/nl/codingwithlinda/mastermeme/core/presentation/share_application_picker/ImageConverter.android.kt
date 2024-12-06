package nl.codingwithlinda.mastermeme.core.presentation.share_application_picker

import android.content.Context
import nl.codingwithlinda.mastermeme.core.data.canvasToByteArray
import nl.codingwithlinda.mastermeme.core.data.dto.MemeDto

actual class ImageConverter(
    private val context: Context
) {

    private fun byteArrayToUri(byteArray: ByteArray): String {
        return nl.codingwithlinda.mastermeme.core.data.byteArrayToUri(byteArray, context)
    }

    actual fun convert(memeDto: MemeDto): String {
        val byteArray = canvasToByteArray(memeDto, context)

        return byteArrayToUri(byteArray)
    }

}