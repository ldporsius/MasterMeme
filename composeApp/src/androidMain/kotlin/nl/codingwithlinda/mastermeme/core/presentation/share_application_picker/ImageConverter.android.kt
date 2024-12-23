package nl.codingwithlinda.mastermeme.core.presentation.share_application_picker

import android.content.Context
import java.io.File

actual class ImageConverter(
    private val context: Context,
) {

    private fun byteArrayToUri(byteArray: ByteArray, name: String): String {
        val path = context.filesDir

        val tmpFile = File(path, "$name.png")

        tmpFile.writeBytes(byteArray)

        return tmpFile.path
    }



    actual fun share(bytes: ByteArray, name: String): String {
        return byteArrayToUri(bytes, name)
    }


}