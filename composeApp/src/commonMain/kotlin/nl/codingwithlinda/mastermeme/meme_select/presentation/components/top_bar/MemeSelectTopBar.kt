package nl.codingwithlinda.mastermeme.meme_select.presentation.components.top_bar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.mastermeme.meme_select.presentation.state.MemeSelectAction

@Composable
fun MemeSelectTopBar(
    numSelected: Int,
    onAction: (MemeSelectAction) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        IconButton(onClick = { onBackClick() }) {
            Icon(imageVector = Icons.Default.Close, contentDescription = null)
        }
        if (numSelected > 0) {
            Text(
                text = "$numSelected",
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { onAction(MemeSelectAction.ShareMemes) }) {
            Icon(imageVector = Icons.Default.Share, contentDescription = null)
        }
        IconButton(onClick = { onAction(MemeSelectAction.DeleteMemes) }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = null)
        }
    }
}