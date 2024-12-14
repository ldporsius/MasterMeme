package nl.codingwithlinda.mastermeme.core.data

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Picture
import android.graphics.Rect
import android.text.StaticLayout
import android.text.TextPaint
import android.util.TypedValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.toComposeRect
import androidx.core.content.res.ResourcesCompat
import nl.codingwithlinda.mastermeme.core.data.dto.MemeDto
import nl.codingwithlinda.mastermeme.core.domain.model.templates.templates
import java.io.ByteArrayOutputStream
import java.io.File
import kotlin.math.roundToInt

fun Picture.toByteArray(): ByteArray {
    if (this.width == 0 || this.height == 0) return ByteArray(0)
    val byteArrayOutputStream = ByteArrayOutputStream()
    pictureToBitmap(this).compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
    return byteArrayOutputStream.toByteArray()
}
fun pictureToBitmap(picture: Picture): Bitmap {
    val bitmap = Bitmap.createBitmap(
        picture.width,
        picture.height,
        Bitmap.Config.ARGB_8888
    )

    val canvas = android.graphics.Canvas(bitmap)
    canvas.drawColor(android.graphics.Color.WHITE)
    canvas.drawPicture(picture)
    return bitmap
}
fun memeDtoToBitmap(memeDto: MemeDto, imageBytes: ByteArray, context: Context): Bitmap {
    val parentWidth = memeDto.parentWidth.roundToInt()
    val parentHeight = memeDto.parentHeight.roundToInt()

    println("PARENT WIDTH: $parentWidth")
    println("PARENT HEIGHT: $parentHeight")

    val density  = context.resources.displayMetrics.density
    //println("DENSITY: $density")


    val bitmapMeme = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
         ?: throw Exception("Could not decode byte array")

//    val memeWidth = bitmapMeme.width * density
//    val memeHeight = bitmapMeme.height * density
//    println("MEME WIDTH: $memeWidth")
//    println("MEME HEIGHT: $memeHeight")

    //val bitmap = Bitmap.createBitmap(memeWidth.roundToInt(), memeHeight.roundToInt(), Bitmap.Config.ARGB_8888)

    val scaledMeme = Bitmap.createScaledBitmap(bitmapMeme, parentWidth, parentHeight, true)
    val canvas = Canvas(scaledMeme)
    canvas.drawBitmap(scaledMeme, 0f, 0f, null)

    println("CANVAS WIDTH: ${canvas.width}")
    println("CANVAS HEIGHT: ${canvas.height}")

    val widthScale = 1
    val heightScale = 1
    //println("WIDTHSCALE: $widthScale")
    //println("HEIGTHSCALE: $heightScale")


    for(memeText in memeDto.memeTexts){
        val _typeface = ResourcesCompat.getFont(context, memeText.fontResource)

        val _textSize = memeText.fontSize
        println("TEXT SIZE: $_textSize")

        val sizeInPixels = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_PX,
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
            color = memeText.textColor
            textSize = sizeInPixels
            textAlign = Paint.Align.LEFT
            typeface = _typeface
            isAntiAlias = true
        }

        val textBounds = Rect()
        paint.getTextBounds(memeText.text, 0, memeText.text.length, textBounds)
        println("TEXT BOUNDS: $textBounds")
        println("TEXT BOUNDS WIDTH: ${textBounds.width()}")
        println("TEXT BOUNDS HEIGHT: ${textBounds.height()}")
        println("TEXT BOUNDS TOP: ${textBounds.top}")
        println("TEXT BOUNDS BOTTOM: ${textBounds.bottom}")
        println("TEXT BOUNDS LEFT: ${textBounds.left}")
        println("TEXT BOUNDS RIGHT: ${textBounds.right}")
        println("TEXT BOUNDS CENTER X: ${textBounds.exactCenterX()}")
        println("TEXT BOUNDS CENTER Y: ${textBounds.exactCenterY()}")
        println("TEXT BOUNDS TO COMPOSE RECT: ${textBounds.toComposeRect()}")

        val textLayout = StaticLayout.Builder.obtain(
            memeText.text,
            0,
            memeText.text.length,
            paint,
            canvas.width
        ).build()

        println("TEXT LAYOUT WIDTH: ${textLayout.width}")
        println("TEXT LAYOUT HEIGHT: ${textLayout.height}")

        //canvas.drawText(memeText.text, offsetX, offsetY - textBounds.centerY(), paint)

        paint.style = Paint.Style.STROKE


        canvas.save()
        canvas.translate(offsetX, offsetY)
        canvas.drawRect(textBounds, paint)

        paint.color = Color.Green.toArgb()

        canvas.drawRect(0f, 0f, textLayout.width.toFloat(),
            textLayout.height.toFloat(), paint)

        paint.style = Paint.Style.FILL

        textLayout.draw(canvas)

        canvas.restore()

    }

    return scaledMeme
}


private fun bitMapToByteArray(bitmap: Bitmap): ByteArray {
    val stream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
    return stream.toByteArray()
}

fun byteArrayToUri(byteArray: ByteArray, name: String,context: Context): String {
    val path = context.filesDir

    val tmpFile = File(path, "$name.png")

    tmpFile.writeBytes(byteArray)

    return tmpFile.path
}