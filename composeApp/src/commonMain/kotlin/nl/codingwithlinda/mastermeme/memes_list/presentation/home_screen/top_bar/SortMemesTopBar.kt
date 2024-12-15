package nl.codingwithlinda.mastermeme.memes_list.presentation.home_screen.top_bar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SortMemesTopBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Your Memes")
        MemeSortOptions()
    }
}

@Composable
fun MemeSortOptions(modifier: Modifier = Modifier) {
    Text("Favorites first",
        style = MaterialTheme.typography.labelLarge
    )
}