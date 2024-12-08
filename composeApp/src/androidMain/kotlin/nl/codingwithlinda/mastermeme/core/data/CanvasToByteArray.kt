package nl.codingwithlinda.mastermeme.core.data

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.os.Build
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.TypedValue
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.scale
import nl.codingwithlinda.mastermeme.core.data.dto.MemeDto
import java.io.ByteArrayOutputStream
import java.io.File
import kotlin.math.roundToInt


fun canvasToByteArray(memeDto: MemeDto, context: Context): ByteArray {
    val parentWidth = memeDto.parentWidth.roundToInt()
    val parentHeight = memeDto.parentHeight.roundToInt()

    val bitmapMeme = BitmapFactory.decodeByteArray(memeDto.imageBytes, 0, memeDto.imageBytes.size)
        ?.scale(parentWidth, parentHeight) ?: throw Exception("Could not decode byte array")

    val bitmap = Bitmap.createBitmap(parentWidth, parentHeight, Bitmap.Config.ARGB_8888)

    val canvas = Canvas(bitmap)
    canvas.drawBitmap(bitmapMeme, 0f, 0f, null)

    val sizeFactor  = 1.0f * canvas.width / parentWidth

    for(memeText in memeDto.memeTexts){
        val _typeface = ResourcesCompat.getFont(context, memeText.fontResource)

        val spSize = (memeText.fontSize.sp).value
        val scaledSizeInPixels = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            spSize, context.resources.displayMetrics
        )

        val paint = TextPaint().apply {
            color = memeText.textColor.toArgb()
            textSize = scaledSizeInPixels
            textAlign = Paint.Align.LEFT
            this.typeface = _typeface
            isAntiAlias = true
        }

        val textBounds = Rect()
        paint.getTextBounds(memeText.text, 0, memeText.text.length, textBounds)

        val textLayout = StaticLayout(
            memeText.text, paint, canvas.width, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false
        )

        val offsetX = (memeText.offsetX * sizeFactor)
        println("OFFSET X AFTER SCALE: $offsetX")

        val offsetY = (memeText.offsetY * sizeFactor) - textLayout.height

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            textLayout.drawText(canvas)
        }
        else{
            canvas.save()
            canvas.translate(offsetX, offsetY)
            textLayout.draw(canvas)
            canvas.restore()
        }


        //canvas.drawText(text, offsetX, offsetY, paint)

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