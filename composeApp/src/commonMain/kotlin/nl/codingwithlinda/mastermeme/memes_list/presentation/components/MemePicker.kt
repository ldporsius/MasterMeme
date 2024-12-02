package nl.codingwithlinda.mastermeme.memes_list.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.mastermeme.core.presentation.templates.MemeTemplateUi

@Composable
fun MemeTemplatePicker(
    templates: List<MemeTemplateUi>,
    onTemplateSelected: (id:String) -> Unit) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ){

        items(templates){template ->
            MemeTemplateItem(
                modifier = Modifier.size(176.dp),
                memeImageUi = template.image,
                onClick = {
                    onTemplateSelected(template.id)
                }
            )
        }
    }
}