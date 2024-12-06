package nl.codingwithlinda.mastermeme.core.data

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.annotation.DrawableRes
import androidx.core.content.FileProvider
import nl.codingwithlinda.mastermeme.R
import nl.codingwithlinda.mastermeme.core.data.dto.MemeDto
import org.jetbrains.compose.resources.DrawableResource
import java.io.ByteArrayOutputStream
import java.io.File
import kotlin.math.roundToInt


fun canvasToByteArray(memeDto: MemeDto, context: Context): ByteArray {
    val bitmapMeme = BitmapFactory.decodeByteArray(memeDto.imageBytes, 0, memeDto.imageBytes.size)

    val bitmap = Bitmap.createBitmap(bitmapMeme.width, bitmapMeme.height, Bitmap.Config.ARGB_8888)

    val canvas = Canvas(bitmap)
    canvas.setBitmap(bitmap)

    canvas.drawBitmap(bitmapMeme, 0f, 0f, null)

    for(memeText in memeDto.memeTexts){
        val paint = Paint()
        paint.color = Color.BLACK
        paint.textSize = memeText.fontSize
        paint.textAlign = Paint.Align.CENTER

        val textHeight = paint.descent() - paint.ascent()
        canvas.drawText(memeText.text, memeText.offsetX, memeText.offsetY, paint)
    }

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