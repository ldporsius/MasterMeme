package nl.codingwithlinda.mastermeme.previews

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nl.codingwithlinda.mastermeme.core.presentation.create_meme.FontPicker
import nl.codingwithlinda.mastermeme.meme_creator.presentation.components.CreatorButtonsComponent
import nl.codingwithlinda.mastermeme.meme_creator.presentation.components.customize_text.CustomizeTextSizeComponent
import nl.codingwithlinda.mastermeme.meme_creator.presentation.components.MemeTextComponentActive
import nl.codingwithlinda.mastermeme.previews.data_generator.fakeMemeTextUI
import nl.codingwithlinda.mastermeme.ui.theme.AppTheme

@Preview
@Composable
fun PreviewMemeTextComponent() {
    AppTheme {
        MemeTextComponentActive(
            modifier = Modifier.fillMaxWidth(),
            text = fakeMemeTextUI(),
            actionOnDelete = {},
            onAction = {}

        )
    }
}
@Preview
@Composable
fun PreviewMemeButtonsComponent() {
    AppTheme {
        CreatorButtonsComponent(
            modifier = Modifier.fillMaxWidth(),
            fontUi = FontPicker().fontResources[0],
            onAction = {}
        )
    }
}
@Preview(showBackground = true, backgroundColor = 0xFF0E0000)
@Composable
fun PreviewMemeTextSizeComponent() {
    AppTheme {
        CustomizeTextSizeComponent(
            memeText = fakeMemeTextUI(),
            onAction = {}
        )
    }
}
