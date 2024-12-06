package nl.codingwithlinda.mastermeme.core.presentation.share_application_picker

import android.content.Context
import nl.codingwithlinda.mastermeme.core.data.dto.MemeDto

actual class ImageConverter(
    private val context: Context
) {
    actual fun canvasToByteArray(drawableRes: Int): ByteArray {
        return nl.codingwithlinda.mastermeme.core.data.canvasToByteArray(drawableRes, context)
    }

    actual fun byteArrayToUri(byteArray: ByteArray): String {
        return nl.codingwithlinda.mastermeme.core.data.byteArrayToUri(byteArray, context)
    }

    actual fun convert(memeDto: MemeDto): String {


       canvasToByteArray(memeDto.imageRef).also {
           return byteArrayToUri(it)
       }
    }

}