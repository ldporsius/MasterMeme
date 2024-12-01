package nl.codingwithlinda.mastermeme.memes_list.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import nl.codingwithlinda.mastermeme.core.presentation.MemeUi

@Composable
fun MemeListItem(
    modifier: Modifier = Modifier,
    memeUi: MemeUi,
    onClick: (String) -> Unit
) {

    Box(
        modifier = modifier
            .clickable(onClick = {
                onClick(memeUi.id)
            })
            .clip(shape = RoundedCornerShape(12))
        ,

    ){
        Box(modifier = Modifier.fillMaxSize()){
            memeUi.image.DrawImage()
        }
    }
}