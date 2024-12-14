package nl.codingwithlinda.mastermeme.core.presentation.share_application_picker

import android.content.Context
import androidx.compose.ui.graphics.asImageBitmap
import nl.codingwithlinda.mastermeme.core.data.dto.MemeDto
import nl.codingwithlinda.mastermeme.core.data.memeDtoToBitmap
import nl.codingwithlinda.mastermeme.core.domain.model.templates.MemeTemplates
import nl.codingwithlinda.mastermeme.core.domain.model.templates.templateToBytes
import nl.codingwithlinda.mastermeme.core.presentation.model.MemeImageUi

actual class ImageConverter(
    private val context: Context,
    private val templates: MemeTemplates
) {

    private fun byteArrayToUri(byteArray: ByteArray): String {
        return nl.codingwithlinda.mastermeme.core.data.byteArrayToUri(byteArray, context)
    }

    actual suspend fun convert(memeDto: MemeDto): String {

        val template = templates.getTemplate(memeDto.imageUri)
        val byteArray = templateToBytes(template.drawableResource)

        return byteArrayToUri(byteArray)
    }

    actual suspend fun memeDtoToUi(memeDto: MemeDto): MemeImageUi {
        val template = templates.getTemplate(memeDto.imageUri)
        val byteArray = templateToBytes(template.drawableResource)

        val bm = memeDtoToBitmap(memeDto, byteArray, context).asImageBitmap()
        val image = MemeImageUi.bitmapImage(bm)
        return  image

    }
    actual fun share(bytes: ByteArray): String {
        return byteArrayToUri(bytes)
    }


}