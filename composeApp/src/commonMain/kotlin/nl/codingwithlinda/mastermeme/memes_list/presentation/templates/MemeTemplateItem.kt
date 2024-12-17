package nl.codingwithlinda.mastermeme.memes_list.presentation.templates

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import nl.codingwithlinda.mastermeme.core.presentation.model.MemeImageUi

@Composable
fun MemeTemplateItem(
    modifier: Modifier = Modifier,
    memeImageUi: MemeImageUi,
    memeName: String,
    onClick: () -> Unit
) {

    Box(
        modifier = modifier
            .clickable(onClick = {
                onClick()
            })
            .clip(shape = RoundedCornerShape(12))
        ,

    ){
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = androidx.compose.ui.Alignment.BottomCenter
        ){
            memeImageUi.DrawThumbnail()
            Text(memeName)
        }
    }
}