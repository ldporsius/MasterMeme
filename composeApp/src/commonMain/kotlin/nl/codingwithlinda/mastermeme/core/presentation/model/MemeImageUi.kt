package nl.codingwithlinda.mastermeme.core.presentation.model

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nl.codingwithlinda.mastermeme.ui.theme.white
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.vectorResource

sealed interface MemeImageUi{
    data class vectorImage(val image: DrawableResource): MemeImageUi {
        override fun width() = Int.MAX_VALUE
        override fun height() = Int.MAX_VALUE
    }

    data class pngImage(val image: DrawableResource): MemeImageUi{
        override fun width() = Int.MAX_VALUE
        override fun height() = Int.MAX_VALUE
    }
    data class bitmapImage(val image: ImageBitmap): MemeImageUi{
        override fun width() = image.width
        override fun height() = image.height
    }

    fun width(): Int
    fun height(): Int

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
                    .fillMaxWidth()
                    .aspectRatio(w / h)
                    .heightIn(h, h)
                ){
                    Image(
                        bitmap = image,
                        contentDescription = null,
                        modifier = Modifier.matchParentSize(),
                        contentScale = ContentScale.Fit
                    )
                }

            }

        }
    }

    @Composable
    fun DrawThumbnail() {
        when(this){
            is bitmapImage -> {

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .background(color = white),
                    contentAlignment = Alignment.Center
                ){
                    Image(
                        bitmap = image,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                }
            }
            is pngImage -> {

            }
            is vectorImage -> {

            }
        }
    }
}
