package nl.codingwithlinda.mastermeme.memes_list.presentation.templates

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.mastermeme.core.domain.model.templates.templates
import nl.codingwithlinda.mastermeme.core.presentation.templates.MemeTemplateUi
import nl.codingwithlinda.mastermeme.memes_list.presentation.state.MemeListViewState
import nl.codingwithlinda.mastermeme.ui.theme.black

@Composable
fun MemeTemplatePicker(
    viewState: MemeListViewState,
    onSearch: (String) -> Unit,
    onTemplateSelected: (id:String) -> Unit,
    onDismiss: () -> Unit
) {

    var shouldShowSearchField by remember {
        mutableStateOf(false)
    }
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
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Choose template:")
                    IconButton(onClick = {
                        shouldShowSearchField = !shouldShowSearchField
                    }) {
                        Icon(Icons.Default.Search, contentDescription = null)
                    }
                }
                Text(
                    "Choose template for your next meme masterpiece",
                    style = MaterialTheme.typography.bodySmall
                )

                AnimatedVisibility(visible = shouldShowSearchField) {
                    Column() {
                        SearchMemeField(
                            onValueChange = {
                                onSearch(it)
                            },
                            onHide = {
                                onSearch("")
                                shouldShowSearchField = false
                            }
                        )

                        Text("${viewState.searchResult.size} templates")
                    }
                }

                LazyVerticalGrid(
                    columns = GridCells.Adaptive(176.dp),
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(16.dp)
                ) {

                    items(viewState.searchResult,
                        key = {
                            it.id
                        }
                    ) { template ->
                        MemeTemplateItem(
                            modifier = Modifier.size(176.dp),
                            memeImageUi = template.image,
                            memeName = template.name,
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

@Composable
fun SearchMemeField(
    onValueChange: (String) -> Unit,
    onHide: () -> Unit
) {

    var value by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TextField(
            value = value,
            onValueChange = {
                value = it
                onValueChange(it)
            },
            modifier = Modifier
                .fillMaxWidth(.95f)
                .align(Alignment.CenterHorizontally)
            ,
            placeholder = {
                Text("Search input")
            },
            leadingIcon = {
                IconButton(onClick = {onHide()}) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                }
            },
            trailingIcon = {
                IconButton(onClick = {
                    value = ""
                    onValueChange("")

                }) {
                    Icon(Icons.Default.Close, contentDescription = null)
                }
            },
            singleLine = true
        )
    }
}