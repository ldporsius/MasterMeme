package nl.codingwithlinda.mastermeme.core.data

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import androidx.annotation.DrawableRes
import androidx.core.content.FileProvider
import nl.codingwithlinda.mastermeme.R
import org.jetbrains.compose.resources.DrawableResource
import java.io.ByteArrayOutputStream
import java.io.File


fun canvasToByteArray(drawableRes: Int, context: Context): ByteArray {
    val bitmap = Bitmap.createBitmap(300, 300, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    canvas.setBitmap(bitmap)

    val drawable = context.getDrawable(drawableRes)
    drawable?.setBounds(0, 0, canvas.width, canvas.height)
    drawable?.draw(canvas)

    return bitMapToByteArray(bitmap, context)
}

fun bitMapToByteArray(bitmap: Bitmap, context: Context): ByteArray {
    val stream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
    return stream.toByteArray()
}

fun byteArrayToUri(byteArray: ByteArray, context: Context): String {
    val path = context.filesDir

    val tmpFile = File(path, "meme.png")

    tmpFile.writeBytes(byteArray)


    return tmpFile.path
}
fun drawableResToBitmap(context: Context, @DrawableRes drawableRes: Int): Bitmap {
   return BitmapFactory.decodeResource(context.resources, drawableRes)
}