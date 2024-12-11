package nl.codingwithlinda.mastermeme.core.data

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.TypedValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.scale
import nl.codingwithlinda.mastermeme.core.data.dto.MemeDto
import java.io.ByteArrayOutputStream
import java.io.File
import kotlin.math.roundToInt

fun memeDtoToBitmap(memeDto: MemeDto, context: Context): Bitmap {
    val parentWidth = memeDto.parentWidth.roundToInt()
    val parentHeight = memeDto.parentHeight.roundToInt()

    val bitmapMeme = BitmapFactory.decodeByteArray(memeDto.imageBytes, 0, memeDto.imageBytes.size)
        ?.scale(parentWidth, parentHeight) ?: throw Exception("Could not decode byte array")

    val bitmap = Bitmap.createBitmap(parentWidth, parentHeight, Bitmap.Config.ARGB_8888)

    val canvas = Canvas(bitmap)
    canvas.drawBitmap(bitmapMeme, 0f, 0f, null)

    val sizeFactor  = 1.0f * canvas.width / parentWidth
    println("SIZE FACTOR: $sizeFactor")

    for(memeText in memeDto.memeTexts){
        val _typeface = ResourcesCompat.getFont(context, memeText.fontResource)

        val spSize = (memeText.fontSize)
        println("SP SIZE: $spSize")

        val sizeInPixels = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            spSize,
            context.resources.displayMetrics
        )

        println("SIZE IN PIXELS: $sizeInPixels")
        val scaledSizeInPixels = sizeInPixels * sizeFactor
        println("SCALED SIZE IN PIXELS: $scaledSizeInPixels")

        val paint = TextPaint().apply {
            color = memeText.textColor.toArgb()
            textSize = scaledSizeInPixels
            textAlign = Paint.Align.LEFT
            typeface = _typeface
            isAntiAlias = true
        }

        val textBounds = Rect()
        paint.getTextBounds(memeText.text, 0, memeText.text.length, textBounds)

        val textLayout = StaticLayout.Builder.obtain(
            memeText.text,
            0,
            memeText.text.length,
            paint,
            canvas.width

        ).build()


        val offsetX = (memeText.offsetX * sizeFactor)
        println("OFFSET X AFTER SCALE: $offsetX")

        val offsetY = (memeText.offsetY * sizeFactor)
        println("TEXT LAYOUT HEIGHT: ${textLayout.height}")
        println("OFFSET Y AFTER SCALE: $offsetY")

        canvas.save()
        canvas.translate(offsetX, offsetY)
        textLayout.draw(canvas)
        canvas.restore()

    }

    return bitmap
}

fun memeDtoToByteArray(memeDto: MemeDto, context: Context): ByteArray {
    val bitmap = memeDtoToBitmap(memeDto, context)
    return bitMapToByteArray(bitmap)
}

private fun bitMapToByteArray(bitmap: Bitmap): ByteArray {
    val stream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
    return stream.toByteArray()
}

fun byteArrayToBitmap(byteArray: ByteArray): ImageBitmap {
    return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size).asImageBitmap()
}

fun memeDtoToImageBitmap(memeDto: MemeDto, context: Context): ImageBitmap {
    val bitmap = memeDtoToBitmap(memeDto, context)
    return bitmap.asImageBitmap()
}
fun byteArrayToUri(byteArray: ByteArray, context: Context): String {
    val path = context.filesDir

    val tmpFile = File(path, "meme.png")

    tmpFile.writeBytes(byteArray)

    return tmpFile.path
}