package nl.codingwithlinda.mastermeme.core.presentation.share_application_picker

import android.content.Context

actual class ImageConverter(
    private val context: Context
) {
    actual fun canvasToByteArray(drawableRes: Int): ByteArray {
        return nl.codingwithlinda.mastermeme.core.data.canvasToByteArray(drawableRes, context)
    }

    actual fun byteArrayToUri(byteArray: ByteArray): String {
        return nl.codingwithlinda.mastermeme.core.data.byteArrayToUri(byteArray, context)
    }


}