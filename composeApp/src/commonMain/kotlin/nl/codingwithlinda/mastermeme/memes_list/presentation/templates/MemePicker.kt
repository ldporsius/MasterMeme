package nl.codingwithlinda.mastermeme.memes_list.presentation.templates

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.mastermeme.core.domain.model.templates.templates
import nl.codingwithlinda.mastermeme.core.presentation.templates.MemeTemplateUi
import nl.codingwithlinda.mastermeme.ui.theme.black

@Composable
fun MemeTemplatePicker(
    templates: List<MemeTemplateUi>,
    onTemplateSelected: (id:String) -> Unit,
    onDismiss: () -> Unit
) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(
            color = black, shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
        )

    ) {
        Box() {
            IconButton(onClick = onDismiss) {
                Icon(Icons.Default.Close, contentDescription = null)
            }
            Column(
                modifier = Modifier.padding(top = 48.dp)
            ) {
                Row {
                    Text("Choostemplate:")
                }
                Text(
                    "Choose template for your next meme masterpiece",
                    style = MaterialTheme.typography.bodySmall
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(16.dp)
                ) {

                    items(templates,
                        key = {
                            it.id
                        }
                    ) { template ->
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
        }
    }
}