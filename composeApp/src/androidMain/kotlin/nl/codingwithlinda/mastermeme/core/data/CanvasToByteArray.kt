package nl.codingwithlinda.mastermeme.core.data

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.text.StaticLayout
import android.text.TextPaint
import android.util.TypedValue
import android.widget.TextView
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asComposePaint
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.core.content.res.ResourcesCompat
import nl.codingwithlinda.mastermeme.R
import nl.codingwithlinda.mastermeme.core.data.dto.MemeDto
import java.io.ByteArrayOutputStream
import java.io.File
import kotlin.math.roundToInt

fun memeDtoToBitmap(memeDto: MemeDto, context: Context): Bitmap {
    val parentWidth = memeDto.parentWidth.roundToInt()
    val parentHeight = memeDto.parentHeight.roundToInt()

    println("PARENT WIDTH: $parentWidth")
    println("PARENT HEIGHT: $parentHeight")

    val density  = context.resources.displayMetrics.density
    println("DENSITY: $density")

    val bitmapMeme = BitmapFactory.decodeByteArray(memeDto.imageBytes, 0, memeDto.imageBytes.size)
         ?: throw Exception("Could not decode byte array")

    val memeWidth = bitmapMeme.width * density
    val memeHeight = bitmapMeme.height * density
    val bitmap = Bitmap.createBitmap(memeWidth.roundToInt(), memeHeight.roundToInt(), Bitmap.Config.ARGB_8888)

    val scaledMeme = Bitmap.createScaledBitmap(bitmapMeme, parentWidth, parentHeight, false)
    val canvas = Canvas(bitmap)
    canvas.drawBitmap(scaledMeme, 0f, 0f, null)

    println("CANVAS WIDTH: ${canvas.width}")
    println("CANVAS HEIGHT: ${canvas.height}")

    val widthScale = 1
    val heightScale = memeHeight / parentHeight
    println("WIDTHSCALE: $widthScale")
    println("HEIGTHSCALE: $heightScale")


    for(memeText in memeDto.memeTexts){
        val _typeface = ResourcesCompat.getFont(context, memeText.fontResource)


        val _textSize = memeText.fontSize
        println("TEXT SIZE: $_textSize")

        val sizeInPixels = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            _textSize,
            context.resources.displayMetrics
        )

         println("SIZE IN PIXELS: $sizeInPixels")

        val offsetX = (memeText.offsetX * widthScale )

        println("OFFSET X: ${memeText.offsetX}")
        println("OFFSET X AFTER SCALE: $offsetX")

        val offsetY = (memeText.offsetY * heightScale )
        println("OFFSET Y: ${memeText.offsetY}")
        println("OFFSET Y AFTER SCALE: $offsetY")


        val paint = TextPaint().apply {
            color = memeText.textColor.toArgb()
            textSize = sizeInPixels
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

        println("TEXT LAYOUT WIDTH: ${textLayout.width}")
        println("TEXT LAYOUT HEIGHT: ${textLayout.height}")

        canvas.drawText(memeText.text, offsetX, offsetY, paint)

        paint.color = Color.Green.toArgb()
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