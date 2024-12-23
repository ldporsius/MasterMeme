package nl.codingwithlinda.mastermeme.core.presentation.create_meme

import android.graphics.Bitmap
import android.graphics.Picture
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.drawscope.draw
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream

@Composable
actual fun PictureDrawer(
    dispatcher: CoroutineDispatcher,
    modifier: Modifier,
    content: @Composable () -> Unit,
    onSave: (ByteArray) -> Unit,
) {

    val scope = rememberCoroutineScope()
    val picture = remember { Picture()}
    Column(
        modifier = modifier
            .drawWithCache {
                // Example that shows how to redirect rendering to an Android Picture and then
                // draw the picture into the original destination
                val width = this.size.width.toInt()
                val height = this.size.height.toInt()
                onDrawWithContent {
                    val pictureCanvas =
                        androidx.compose.ui.graphics.Canvas(
                            picture.beginRecording(
                                width,
                                height
                            )
                        )
                    draw(this, this.layoutDirection, pictureCanvas, this.size) {
                        this@onDrawWithContent.drawContent()
                    }
                    picture.endRecording()

                    drawIntoCanvas { canvas -> canvas.nativeCanvas.drawPicture(picture) }

                    scope.launch {
                        onSave(picture.toByteArray(dispatcher))
                    }

                }
            }
    ) {
        content()
    }
}
private suspend fun Picture.toByteArray(dispatcher: CoroutineDispatcher): ByteArray {
    if (this.width == 0 || this.height == 0) return ByteArray(0)

    val picture = this
    return withContext( dispatcher) {

        val byteArrayOutputStream = ByteArrayOutputStream()
        pictureToBitmap(picture).compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
       byteArrayOutputStream.toByteArray()
    }
}

private fun pictureToBitmap(picture: Picture): Bitmap {
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
