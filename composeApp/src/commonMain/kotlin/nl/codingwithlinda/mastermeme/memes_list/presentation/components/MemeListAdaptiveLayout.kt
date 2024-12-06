package nl.codingwithlinda.mastermeme.memes_list.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.mastermeme.core.presentation.model.MemeUi

@Composable
fun MemeListAdaptiveLayout(
    modifier: Modifier = Modifier,
    memes: List<MemeUi>,
    onMemeClick: (id: String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 176.dp),
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ){

        items(memes){
            MemeListItem(
                memeUi = it,
                onClick = onMemeClick
            )
        }
    }
}