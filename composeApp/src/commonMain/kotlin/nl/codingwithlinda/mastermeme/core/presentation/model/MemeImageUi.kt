package nl.codingwithlinda.mastermeme.core.presentation.model

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.vectorResource

sealed interface MemeImageUi{
    data class vectorImage(val image: DrawableResource): MemeImageUi
    data class pngImage(val image: DrawableResource): MemeImageUi
    data class bitmapImage(val image: ImageBitmap): MemeImageUi

    @Composable
    fun DrawImage() {
        when(this){
            is pngImage -> Image(
                painter = painterResource(image),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Fit
                )
            is vectorImage -> Image(
                imageVector = vectorResource(image),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
            is bitmapImage -> {
                val h = image.height.dp
                val w = image.width.dp
                Box(modifier = Modifier
                    .width(w)
                    .height(h)
                ){
                    Image(
                        painter = BitmapPainter(
                            image,
                            IntOffset(0, 0),
                            IntSize(image.width, image.height)
                        ),
                        contentDescription = null,
                        modifier = Modifier,
                        contentScale = ContentScale.Inside
                    )
                    /*Image(
                        bitmap = image,
                        contentDescription = null,
                        modifier = Modifier,
                        contentScale = ContentScale.Fit
                    )*/
                }

            }

        }
    }
}
