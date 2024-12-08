package nl.codingwithlinda.mastermeme.previews

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import mastermeme.composeapp.generated.resources.Res
import mastermeme.composeapp.generated.resources.vector_18
import nl.codingwithlinda.mastermeme.core.presentation.model.MemeImageUi
import nl.codingwithlinda.mastermeme.core.presentation.share_application_picker.ShareAppPicker
import nl.codingwithlinda.mastermeme.meme_creator.presentation.components.CreatorButtonsComponent
import nl.codingwithlinda.mastermeme.meme_creator.presentation.components.edit.EditTextSizeComponent
import nl.codingwithlinda.mastermeme.meme_creator.presentation.components.MemeCreatorScreen
import nl.codingwithlinda.mastermeme.meme_creator.presentation.components.MemeTextComponentActive
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorViewState
import nl.codingwithlinda.mastermeme.meme_creator.presentation.ui_model.MemeUiText
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
            onAction = {}
        )
    }
}
@Preview(showBackground = true, backgroundColor = 0xFF0E0000)
@Composable
fun PreviewMemeTextSizeComponent() {
    AppTheme {
        EditTextSizeComponent(
            memeText = fakeMemeTextUI(),
            onAction = {}
        )
    }
}

@Preview
@Composable
fun PreviewMemeCreator() {

AppTheme {
    MemeCreatorScreen(
        modifier = Modifier.fillMaxSize(),
        state = MemeCreatorViewState(
            memeImageUi = MemeImageUi.vectorImage(Res.drawable.vector_18),
        ),
        colors = listOf(
            androidx.compose.ui.graphics.Color.Black,
            androidx.compose.ui.graphics.Color.White,
        ),
        fonts = listOf(

        ),
        shareAppPicker = ShareAppPicker(LocalContext.current),
        onAction = {},
    )
}
}