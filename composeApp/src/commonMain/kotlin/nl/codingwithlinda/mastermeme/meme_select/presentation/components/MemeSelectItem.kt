package nl.codingwithlinda.mastermeme.meme_select.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.mastermeme.core.presentation.model.MemeUi
import nl.codingwithlinda.mastermeme.ui.theme.white

@Composable
fun MemeSelectItem(
    memeUi: MemeUi,
    isSelected: Boolean,
    onClick: (memeId: String) -> Unit,
    modifier: Modifier = Modifier
) {

    val iconTint = if(isSelected){
       white
    }else{
        androidx.compose.ui.graphics.Color.Black
    }

    Box(
        contentAlignment = androidx.compose.ui.Alignment.TopEnd
    ) {
        memeUi.image.DrawThumbnail()
        Icon(imageVector = Icons.Default.CheckCircle,
            contentDescription = null,
            modifier = Modifier.padding(16.dp).clickable {
                onClick(memeUi.id)
            },
            tint = iconTint
            )

    }

}