package nl.codingwithlinda.mastermeme.memes_list.presentation.home_screen.top_bar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SortMemesTopBar(
    sortOptions: List<MemeSortOption>,
    selectedSortOption: MemeSortOption,
    selectSortOption: (MemeSortOption) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(text = "Your Memes")
        MemeSortOptions(
            sortOptions = sortOptions,
            selectedSortOption = selectedSortOption,
            selectSortOption = selectSortOption
        )
    }
}

@Composable
fun MemeSortOptions(
    sortOptions: List<MemeSortOption>,
    selectedSortOption: MemeSortOption,
    selectSortOption: (MemeSortOption) -> Unit
) {

    var showSortOptions by remember {
        mutableStateOf(false)
    }
    val textSelected = memeSortOptionToUiText[selectedSortOption]?.asString() ?: ""
    Column(
        modifier = Modifier.width(IntrinsicSize.Max),
        horizontalAlignment = Alignment.End
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable {
                showSortOptions = !showSortOptions
            }
        ) {
            Text(
                textSelected,
                style = MaterialTheme.typography.labelLarge
            )
            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
        }

        Box(
            modifier = Modifier
                .width(IntrinsicSize.Max)
                ,
            contentAlignment = Alignment.TopEnd
        ) {
            DropdownMenu(
                expanded = showSortOptions,
                onDismissRequest = {
                    showSortOptions = false
                },
                modifier = Modifier.wrapContentSize(),

            ) {
                sortOptions.forEach { option ->
                    val text = memeSortOptionToUiText.get(option)?.asString() ?: ""
                    DropdownMenuItem(
                        text = {
                            Text(text = text)
                        },
                        onClick = {
                            selectSortOption(option)
                        },
                        modifier = Modifier

                    )
                }
            }
        }
    }

}