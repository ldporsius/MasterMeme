package nl.codingwithlinda.mastermeme.previews

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mastermeme.composeapp.generated.resources.Res
import mastermeme.composeapp.generated.resources._1_49su9f
import mastermeme.composeapp.generated.resources._4_scared
import nl.codingwithlinda.mastermeme.core.presentation.model.MemeImageUi
import nl.codingwithlinda.mastermeme.core.presentation.model.MemeUi
import nl.codingwithlinda.mastermeme.memes_list.presentation.components.EmptyListComponent
import nl.codingwithlinda.mastermeme.memes_list.presentation.home_screen.MemeListAdaptiveLayout
import nl.codingwithlinda.mastermeme.memes_list.presentation.home_screen.MemeListItem
import nl.codingwithlinda.mastermeme.previews.data_generator.fakeMemeUi
import nl.codingwithlinda.mastermeme.ui.theme.AppTheme

@Preview
@Composable
fun MemesListEmptyComponentPreview() {
    AppTheme {
        EmptyListComponent()
    }
}

@Preview
@Composable
fun MemesListItemPreview() {
    AppTheme {
        MemeListItem(
            modifier = Modifier.size(200.dp),
            memeUi = fakeMemeUi(),
            onLongPress = {},
            onClick = {}
        )
    }
}


@Preview
@Composable
fun MemesListPreview() {
    AppTheme {
        MemeListAdaptiveLayout(
            modifier = Modifier,
            memes = listOf(
                fakeMemeUi(),
               fakeMemeUi()
            ),
            onMemeClick = {},
            onMemeLongPress = {}

        )
    }
}