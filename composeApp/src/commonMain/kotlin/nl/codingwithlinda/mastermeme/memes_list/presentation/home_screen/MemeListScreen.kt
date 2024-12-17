package nl.codingwithlinda.mastermeme.memes_list.presentation.home_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import nl.codingwithlinda.mastermeme.memes_list.presentation.components.EmptyListComponent
import nl.codingwithlinda.mastermeme.memes_list.presentation.templates.MemeTemplatePicker
import nl.codingwithlinda.mastermeme.memes_list.presentation.home_screen.top_bar.SortMemesTopBar
import nl.codingwithlinda.mastermeme.memes_list.presentation.state.MemeListAction
import nl.codingwithlinda.mastermeme.memes_list.presentation.state.MemeListViewState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemeListScreen(
    state: MemeListViewState,
    onAction: (MemeListAction) -> Unit,

) {
    Scaffold(
        modifier = Modifier.fillMaxSize().safeContentPadding(),

        floatingActionButton = {
            FloatingActionButton(onClick = {
                onAction(MemeListAction.ShowMemePicker)
            }){
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        },
        floatingActionButtonPosition = androidx.compose.material3.FabPosition.End,

        ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            SortMemesTopBar(
                sortOptions = state.sortOptions,
                selectedSortOption = state.selectedSortOption,
                selectSortOption = {
                    onAction(MemeListAction.SortMemes(it))
                }

            )

            if(state.isLoading){
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ){
                    CircularProgressIndicator()
                }
            }
            if(state.error != null){
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = androidx.compose.ui.Alignment.Center){
                    Text(state.error.asString())
                }
            }
            if(state.memes.isEmpty()){
                EmptyListComponent(
                    modifier = Modifier.fillMaxSize()
                )
            }
            MemeListAdaptiveLayout(
                memes = state.sortedMemes,
                onMemeClick = {
                    onAction(MemeListAction.MemeClicked(it))
                },
                onMemeLongPress = {
                    onAction(MemeListAction.MemeLongPressed(it))
                }
            )
        }

        val skipPartiallyExpanded by rememberSaveable { mutableStateOf(true) }
        val scope = rememberCoroutineScope()
        val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = skipPartiallyExpanded)
        if(state.showMemePicker){
            ModalBottomSheet(
                onDismissRequest = {
                    onAction(MemeListAction.HideMemePicker)
                },
                sheetState = bottomSheetState
            ){
                MemeTemplatePicker(
                    templates = state.templates,
                    onTemplateSelected = {
                        onAction(MemeListAction.CreateNewMeme(it))
                    }
                )
            }
        }
    }

}