package nl.codingwithlinda.mastermeme.meme_select.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import nl.codingwithlinda.mastermeme.core.presentation.model.MemeUi

@Composable
fun MemeSelectItem(
    memeUi: MemeUi,
    modifier: Modifier = Modifier
) {


    memeUi.image.DrawThumbnail()

}