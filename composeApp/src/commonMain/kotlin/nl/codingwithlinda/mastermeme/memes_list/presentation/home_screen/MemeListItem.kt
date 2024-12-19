package nl.codingwithlinda.mastermeme.memes_list.presentation.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.input.pointer.pointerInput
import nl.codingwithlinda.mastermeme.core.presentation.model.MemeUi
import nl.codingwithlinda.mastermeme.ui.theme.black
import nl.codingwithlinda.mastermeme.ui.theme.white

@Composable
fun MemeListItem(
    modifier: Modifier = Modifier,
    memeUi: MemeUi,
    onLongPress: (String) -> Unit,
    onClick: (String) -> Unit
) {

    val iconTint = if(memeUi.isFavorite){
        androidx.compose.ui.graphics.Color.Red
    }else{
       white
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
        memeUi.image.DrawThumbnail()
        Box(modifier = Modifier
            .matchParentSize()
            .background(
                brush = Brush.verticalGradient(
                colors = listOf(
                    androidx.compose.ui.graphics.Color.Transparent,
                    black.copy(.5f)
                )
            ))
            ,
            contentAlignment = androidx.compose.ui.Alignment.BottomEnd
        ) {

            IconButton(onClick = { onClick(memeUi.id) }) {
                Icon(
                    imageVector = Icons.Outlined.Favorite,
                    contentDescription = null,
                    tint = iconTint
                )
            }
        }
    }
}