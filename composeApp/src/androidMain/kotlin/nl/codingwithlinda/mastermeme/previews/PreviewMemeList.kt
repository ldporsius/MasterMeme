package nl.codingwithlinda.mastermeme.previews

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mastermeme.composeapp.generated.resources.Res
import mastermeme.composeapp.generated.resources._0_1otri4
import nl.codingwithlinda.mastermeme.core.presentation.MemeImageUi
import nl.codingwithlinda.mastermeme.memes_list.presentation.components.EmptyListComponent
import nl.codingwithlinda.mastermeme.memes_list.presentation.components.MemesListItem
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
        MemesListItem(
            modifier = Modifier.size(200.dp),
            memeImage = MemeImageUi.pngImage(Res.drawable._0_1otri4)
        ) { }
    }
}