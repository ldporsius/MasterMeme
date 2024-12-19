package nl.codingwithlinda.mastermeme.memes_list.presentation.home_screen

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
import nl.codingwithlinda.mastermeme.meme_select.presentation.components.MemeSelectItem

@Composable
fun MemeListAdaptiveLayout(
    modifier: Modifier = Modifier,
    memes: List<MemeUi>,
    isSelecting: Boolean,
    isSelected: (memeId: String) -> Boolean,
    toggleMemeSelection: (memeId: String) -> Unit,
    onMemeClick: (id: String) -> Unit,
    onMemeLongPress: (id: String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 176.dp),
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(bottom = 128.dp)
    ){

        items(memes){
            if (isSelecting){
                MemeSelectItem(
                    memeUi = it,
                    onClick = toggleMemeSelection,
                    isSelected = isSelected(it.id)
                )
            }else {
                MemeListItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    memeUi = it,
                    onClick = onMemeClick,
                    onLongPress = onMemeLongPress
                )
            }
        }
    }
}