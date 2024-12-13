package nl.codingwithlinda.mastermeme.core.presentation.create_meme

import android.graphics.Picture
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.draw
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import nl.codingwithlinda.mastermeme.core.data.toByteArray
import nl.codingwithlinda.mastermeme.meme_creator.presentation.components.MemeTextComponent
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorAction
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorViewState

@Composable
actual fun PictureDrawer(
    state: MemeCreatorViewState,
    ourPlatformTextStyle: OurPlatformTextStyle,
    onAction: (MemeCreatorAction) -> Unit,
    onSave: (ByteArray) -> Unit,
) {

    val picture = remember { Picture()}
    Column(
        modifier = Modifier
            .fillMaxWidth()
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

                    onSave(picture.toByteArray())
                }
            }
    ) {
        BoxWithConstraints(
            modifier = Modifier
        ) {
            state.memeImageUi.DrawImage()
            state.memeTexts.onEach { memeText ->
                MemeTextComponent(
                    text = memeText.value,
                    platformTextStyle = ourPlatformTextStyle,
                    parentSize = Size(constraints.maxWidth.toFloat(), constraints.maxHeight.toFloat()),
                    onAction = {}
                )
            }
        }
    }
}
