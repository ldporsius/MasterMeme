package nl.codingwithlinda.mastermeme.previews

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import mastermeme.composeapp.generated.resources.Res
import mastermeme.composeapp.generated.resources.vector_18
import nl.codingwithlinda.mastermeme.core.presentation.MemeImageUi
import nl.codingwithlinda.mastermeme.meme_creator.presentation.components.CreatorButtonsComponent
import nl.codingwithlinda.mastermeme.meme_creator.presentation.components.EditTextSizeComponent
import nl.codingwithlinda.mastermeme.meme_creator.presentation.components.MemeCreatorScreen
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorViewState
import nl.codingwithlinda.mastermeme.ui.theme.AppTheme

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
@Preview
@Composable
fun PreviewMemeTextSizeComponent() {
    AppTheme {
        EditTextSizeComponent()
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
        onAction = {}
    )
}
}