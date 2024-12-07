package nl.codingwithlinda.mastermeme.core.presentation.model

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
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
                val h = image.height.toFloat()
                val w = image.width.toFloat()
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(w/h)
                ){
                    Image(
                        bitmap = image,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )
                }

            }

        }
    }
}
