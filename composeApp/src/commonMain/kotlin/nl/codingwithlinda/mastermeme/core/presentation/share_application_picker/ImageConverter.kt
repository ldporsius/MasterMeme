package nl.codingwithlinda.mastermeme.core.presentation.share_application_picker


expect class ImageConverter {

    fun canvasToByteArray(drawableRes: Int): ByteArray
    fun byteArrayToUri(byteArray: ByteArray): String

}