package nl.codingwithlinda.mastermeme.core.presentation.model

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.vectorResource

sealed interface MemeImageUi{
    data class vectorImage(val image: DrawableResource): MemeImageUi
    data class pngImage(val image: DrawableResource): MemeImageUi

    @Composable
    fun DrawImage() {
        when(this){
            is pngImage -> Image(
                painter = painterResource(image),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
                )
            is vectorImage -> Image(
                imageVector = vectorResource(image),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )

        }
    }
}
