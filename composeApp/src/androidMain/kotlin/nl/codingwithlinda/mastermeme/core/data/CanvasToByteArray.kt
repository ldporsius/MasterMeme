package nl.codingwithlinda.mastermeme.core.data

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Environment
import android.text.TextPaint
import android.util.TypedValue
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.scale
import nl.codingwithlinda.mastermeme.R
import nl.codingwithlinda.mastermeme.core.data.dto.MemeDto
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.decodeToImageBitmap
import java.io.ByteArrayOutputStream
import java.io.File
import kotlin.math.abs
import kotlin.math.roundToInt


@OptIn(ExperimentalResourceApi::class)
@Composable
fun MemeCanvas(
    modifier: Modifier = Modifier,
    memeDto: MemeDto,
) {
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
    val parentWidth = memeDto.parentWidth.roundToInt()
    val parentHeight = memeDto.parentHeight.roundToInt()

    println("PARENT WIDTH: $parentWidth")
    println("PARENT HEIGHT: $parentHeight")


    val bitmapMeme = BitmapFactory.decodeByteArray(memeDto.imageBytes, 0, memeDto.imageBytes.size)
        ?.scale(parentWidth, parentHeight) ?: throw Exception("Could not decode byte array")

    println("BITMAP MEME WIDTH: ${bitmapMeme.width}")
    println("BITMAP MEME HEIGHT: ${bitmapMeme.height}")

    val bitmap = Bitmap.createBitmap(parentWidth, parentHeight, Bitmap.Config.ARGB_8888)

    println("BITMAP WIDTH: ${bitmap.width}")
    println("BITMAP HEIGHT: ${bitmap.height}")

    val canvas = Canvas(bitmap)

    println("CANVAS WIDTH: ${canvas.width}")
    println("CANVAS HEIGHT: ${canvas.height}")

    canvas.drawBitmap(bitmapMeme, 0f, 0f, null)

    val typefaceImpact = ResourcesCompat.getFont(context, R.font.impact)
    val sizeFactor  = 1.0f * canvas.width / parentWidth

    for(memeText in memeDto.memeTexts){

        val spSize = (memeText.fontSize.sp).value
        val scaledSizeInPixels = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            spSize, context.resources.displayMetrics
        )

        val paint = TextPaint().apply {
            color = Color.BLACK
            textSize = scaledSizeInPixels
            textAlign = Paint.Align.LEFT
            this.typeface = typefaceImpact
            isAntiAlias = true
        }

        val texts = memeText.text.split("\n")
        var summedHeight = 0f
        for(text in texts){
            val textBounds = Rect()
            paint.getTextBounds(text, 0, text.length, textBounds)

            val offsetX = (memeText.offsetX * sizeFactor)
            println("OFFSET X AFTER SCALE: $offsetX")

            val offsetY = ((memeText.offsetY) * sizeFactor) - textBounds.top + summedHeight
            println("OFFSET Y AFTER SCALE: $offsetY")
            summedHeight += textBounds.height()


            canvas.drawText(text, offsetX, offsetY, paint)

        }

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