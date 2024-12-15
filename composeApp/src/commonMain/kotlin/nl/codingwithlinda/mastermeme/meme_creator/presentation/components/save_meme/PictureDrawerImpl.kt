package nl.codingwithlinda.mastermeme.meme_creator.presentation.components.save_meme

import androidx.compose.runtime.Composable
import nl.codingwithlinda.mastermeme.core.presentation.create_meme.PictureDrawer
import nl.codingwithlinda.mastermeme.core.presentation.model.MemeImageUi
import nl.codingwithlinda.mastermeme.meme_creator.presentation.ui_model.MemeUiText

@Composable
fun PictureDrawerImpl(
    memeImageUi: MemeImageUi,
    memeTexts: List<MemeUiText>,
    onSave: (ByteArray) -> Unit,
){
    PictureDrawer(
        content = {
            PictureDrawerContent(
                memeImageUi = memeImageUi,
                memeTexts = memeTexts,
            )
        },
        onSave = onSave,
    )
}
