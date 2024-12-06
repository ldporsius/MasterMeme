package nl.codingwithlinda.mastermeme.core.data

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.TextPaint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import nl.codingwithlinda.mastermeme.R
import nl.codingwithlinda.mastermeme.core.data.dto.MemeDto
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.decodeToImageBitmap
import java.io.ByteArrayOutputStream
import java.io.File
import kotlin.math.roundToInt

@OptIn(ExperimentalResourceApi::class)
@Composable
fun MemeCanvas(
    modifier: Modifier = Modifier,
               memeDto: MemeDto,) {
    val bitmap = memeDto.imageBytes.decodeToImageBitmap()
    val textMeasurer = rememberTextMeasurer()
    Canvas(modifier = modifier.size(
        width = bitmap.width.dp,
        height = bitmap.height.dp
    )
    ) {
        drawImage(bitmap)
        for(memeText in memeDto.memeTexts){
            drawText(
                textMeasurer = textMeasurer,
                text = memeText.text,
                topLeft = Offset(memeText.offsetX, memeText.offsetY)
            )
        }

    }
}

fun canvasToByteArray(memeDto: MemeDto, context: Context): ByteArray {
    val bitmapMeme = BitmapFactory.decodeByteArray(memeDto.imageBytes, 0, memeDto.imageBytes.size)

    println("BITMAP MEME WIDTH: ${bitmapMeme.width}")
    println("BITMAP MEME HEIGHT: ${bitmapMeme.height}")

    val originalWidth = memeDto.memeTexts.maxOfOrNull {
        it.parentWidth.roundToInt()
    } ?: 1080
    val originalHeight = memeDto.memeTexts.maxOfOrNull {
        it.parentHeight.roundToInt()
    } ?: 1080

    val widthScaleFactor = bitmapMeme.width.toFloat() / originalWidth.toFloat()
    val heightScaleFactor = bitmapMeme.height.toFloat() / originalHeight.toFloat()

    val bitmap = Bitmap.createBitmap(bitmapMeme.width, bitmapMeme.height, Bitmap.Config.ARGB_8888)
    println("BITMAP WIDTH: ${bitmap.width}")
    println("BITMAP HEIGHT: ${bitmap.height}")

    val canvas = Canvas(bitmap)

    println("CANVAS WIDTH: ${canvas.width}")
    println("CANVAS HEIGHT: ${canvas.height}")

    canvas.setBitmap(bitmap)

    println("CANVAS HEIGHT AFTER SET: ${canvas.height}")

    canvas.drawBitmap(bitmapMeme, 0f, 0f, null)

    val typefaceImpact = ResourcesCompat.getFont(context, R.font.impact)

    for(memeText in memeDto.memeTexts){
        val fontSizeFactor  = memeText.fontSize/ canvas.width
        val scaledFontSize = memeText.fontSize * fontSizeFactor

        val paint = TextPaint().apply {
            color = Color.BLACK
            textSize = scaledFontSize
            textAlign = Paint.Align.LEFT
            this.typeface = typefaceImpact
            isAntiAlias = true
        }

        println("FONT SIZE: ${memeText.fontSize}")
        println("FONT SIZE FACTOR: $fontSizeFactor")
        println("FONT SIZE AFTER SCALE: $scaledFontSize")
        println("OFFSET X: ${memeText.offsetX}")
        println("OFFSET Y: ${memeText.offsetY}")
        println("PARENT HEIGHT: ${memeText.parentHeight}")
        println("PARENT WIDTH: ${memeText.parentWidth}")

        val offsetX = (memeText.offsetX * widthScaleFactor)
        println("OFFSET X AFTER SCALE: $offsetX")
        val offsetY = (memeText.offsetY * heightScaleFactor)
        println("OFFSET Y AFTER SCALE: $offsetY")
        canvas.drawText(memeText.text, offsetX, offsetY, paint)
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