package nl.codingwithlinda.mastermeme.memes_list.presentation.home_screen

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import nl.codingwithlinda.mastermeme.core.presentation.model.MemeUi

@Composable
fun MemeListItem(
    modifier: Modifier = Modifier,
    memeUi: MemeUi,
    onLongPress: (String) -> Unit,
    onClick: (String) -> Unit
) {

    println("memeUi.isFavorite: ${memeUi.isFavorite}")
    val iconTint = if(memeUi.isFavorite){
        androidx.compose.ui.graphics.Color.Red
    }else{
        androidx.compose.ui.graphics.Color.Unspecified
    }
    Box(
        modifier = modifier
            .pointerInput(Unit){
                this.detectTapGestures(
                    onLongPress = {
                        onLongPress(memeUi.id)
                    }
                )
            }
            .clip(shape = RoundedCornerShape(12))
        ){
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = androidx.compose.ui.Alignment.BottomEnd
        ){
            memeUi.image.DrawThumbnail()
            IconButton(onClick = { onClick(memeUi.id)}) {
                Icon(imageVector = Icons.Default.Favorite,
                    contentDescription = null,
                    tint = iconTint
                )
            }
        }
    }
}