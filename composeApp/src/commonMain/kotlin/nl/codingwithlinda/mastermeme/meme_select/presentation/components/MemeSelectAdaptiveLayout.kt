package nl.codingwithlinda.mastermeme.meme_select.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.mastermeme.core.presentation.model.MemeUi
import nl.codingwithlinda.mastermeme.meme_select.presentation.state.MemeSelectViewState
import nl.codingwithlinda.mastermeme.memes_list.presentation.home_screen.MemeListItem

@Composable
fun MemeSelectAdaptiveLayout(
    modifier: Modifier = Modifier,
    viewState: MemeSelectViewState,
    memes: List<MemeUi>,
    onMemeClick: (id: String) -> Unit,

) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 176.dp),
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ){

        items(memes){
            MemeSelectItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                ,
                memeUi = it,
                isSelected = viewState.isSelected(it.id),
                onClick = {
                    onMemeClick(it)
                }

            )
        }
    }
}