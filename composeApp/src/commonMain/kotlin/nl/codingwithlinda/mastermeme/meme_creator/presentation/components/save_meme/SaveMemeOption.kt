package nl.codingwithlinda.mastermeme.meme_creator.presentation.components.save_meme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SaveMemeOption(
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,
    title: String,
    text: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier.clickable(onClick = onClick),
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(120.dp),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ){
            icon()
        }

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = title, style = MaterialTheme.typography.titleLarge)
            Text(text = text)

        }
    }
}