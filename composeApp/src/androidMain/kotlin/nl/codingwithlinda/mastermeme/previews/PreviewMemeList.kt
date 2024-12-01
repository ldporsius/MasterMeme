package nl.codingwithlinda.mastermeme.previews

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mastermeme.composeapp.generated.resources.Res
import mastermeme.composeapp.generated.resources._0_1otri4
import nl.codingwithlinda.mastermeme.core.presentation.MemeImageUi
import nl.codingwithlinda.mastermeme.core.presentation.MemeUi
import nl.codingwithlinda.mastermeme.memes_list.presentation.components.EmptyListComponent
import nl.codingwithlinda.mastermeme.memes_list.presentation.components.MemeListAdaptiveLayout
import nl.codingwithlinda.mastermeme.memes_list.presentation.components.MemeListItem
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
            memeUi = MemeUi(
                id = "1",
                name = "meme1",
                dateCreated = 123456789,
                image = MemeImageUi.pngImage(Res.drawable._0_1otri4)
            )
        ) { }
    }
}


@Preview
@Composable
fun MemesListPreview() {
    AppTheme {
        MemeListAdaptiveLayout(
            modifier = Modifier,
            memes = listOf(
                MemeUi(
                    id = "1",
                    name = "meme1",
                    dateCreated = 123456789,
                    image = MemeImageUi.pngImage(Res.drawable._0_1otri4)

                ),
                MemeUi(
                    id = "2",
                    name = "meme2",
                    dateCreated = 123456789,
                    image = MemeImageUi.pngImage(Res.drawable._0_1otri4)
                )
            ),
            onMemeClick = {}
        )
    }
}