package nl.codingwithlinda.mastermeme.core.presentation.share_application_picker

import android.content.Context
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import nl.codingwithlinda.mastermeme.core.data.memeDtoToByteArray
import nl.codingwithlinda.mastermeme.core.data.dto.MemeDto
import nl.codingwithlinda.mastermeme.core.data.memeDtoToBitmap
import nl.codingwithlinda.mastermeme.core.presentation.model.MemeImageUi

actual class ImageConverter(
    private val context: Context
) {

    private fun byteArrayToUri(byteArray: ByteArray): String {
        return nl.codingwithlinda.mastermeme.core.data.byteArrayToUri(byteArray, context)
    }

    actual fun imageWidth(imageBitmap: ImageBitmap): Float {
       return imageBitmap.width.toFloat()
    }
    actual fun convert(memeDto: MemeDto): String {
        val byteArray = memeDtoToByteArray(memeDto, context)

        return byteArrayToUri(byteArray)
    }

    actual fun memeDtoToUi(memeDto: MemeDto): MemeImageUi {
        val bm = memeDtoToBitmap(memeDto, context).asImageBitmap()
        val image = MemeImageUi.bitmapImage(bm)
        return  image

    }
    actual fun share(bytes: ByteArray): String {
        return byteArrayToUri(bytes)
    }


}