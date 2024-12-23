package nl.codingwithlinda.mastermeme.core.data.local_cache

import android.content.Context
import androidx.core.content.FileProvider
import java.io.File

actual class InternalStorageInteractor(
    private val context: Context
) {
   /* actual fun write(fileName: String, bytes: ByteArray) {
        context.openFileOutput(fileName, Context.MODE_PRIVATE).use { outputStream ->
            outputStream.write(bytes)
        }
    }*/
    actual fun read(fileName: String): ByteArray {
        val file = File(fileName)

        val uri =
            FileProvider.getUriForFile(context, "nl.codingwithlinda.mastermeme.fileprovider", file)

        context.contentResolver.openInputStream(uri)?.use { inputStream ->
            return inputStream.readBytes()
        }
        return ByteArray(0)

    }

}