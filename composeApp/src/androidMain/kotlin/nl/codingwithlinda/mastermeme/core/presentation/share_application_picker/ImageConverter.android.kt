package nl.codingwithlinda.mastermeme.core.presentation.share_application_picker

import android.content.Context
import androidx.compose.ui.graphics.asImageBitmap
import nl.codingwithlinda.mastermeme.core.data.dto.MemeDto
import nl.codingwithlinda.mastermeme.core.data.memeDtoToBitmap
import nl.codingwithlinda.mastermeme.core.domain.model.templates.MemeTemplatesProvider
import nl.codingwithlinda.mastermeme.core.domain.model.templates.templateToBytes
import nl.codingwithlinda.mastermeme.core.presentation.model.MemeImageUi

actual class ImageConverter(
    private val context: Context,
    private val templates: MemeTemplatesProvider
) {

    private fun byteArrayToUri(byteArray: ByteArray, name: String): String {
        return nl.codingwithlinda.mastermeme.core.data.byteArrayToUri(byteArray, name, context)
    }

    actual suspend fun convert(memeDto: MemeDto): String {

        val template = templates.getTemplate(memeDto.imageUri)
        val byteArray = templateToBytes(template.drawableResource)

        return byteArrayToUri(byteArray, memeDto.imageUri)
    }

    actual suspend fun memeDtoToUi(memeDto: MemeDto): MemeImageUi {
        val template = templates.getTemplate(memeDto.imageUri)
        val byteArray = templateToBytes(template.drawableResource)

        val bm = memeDtoToBitmap(memeDto, byteArray, context).asImageBitmap()
        val image = MemeImageUi.bitmapImage(bm)
        return  image

    }
    actual fun share(bytes: ByteArray, name: String): String {
        return byteArrayToUri(bytes, name)
    }


}