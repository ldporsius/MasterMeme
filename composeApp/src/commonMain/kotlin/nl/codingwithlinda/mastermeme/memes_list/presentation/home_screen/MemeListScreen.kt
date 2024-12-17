package nl.codingwithlinda.mastermeme.memes_list.presentation.home_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.mastermeme.memes_list.presentation.components.EmptyListComponent
import nl.codingwithlinda.mastermeme.memes_list.presentation.home_screen.top_bar.SortMemesTopBar
import nl.codingwithlinda.mastermeme.memes_list.presentation.state.MemeListAction
import nl.codingwithlinda.mastermeme.memes_list.presentation.state.MemeListViewState
import nl.codingwithlinda.mastermeme.memes_list.presentation.templates.MemeTemplatePicker

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemeListScreen(
    state: MemeListViewState,
    onAction: (MemeListAction) -> Unit,

) {
    val scaffoldState = rememberBottomSheetScaffoldState()

    Scaffold(
        modifier = Modifier.fillMaxSize().safeContentPadding(),

       /* floatingActionButton = {
            FloatingActionButton(onClick = {
                onAction(MemeListAction.ShowMemePicker)
            }){
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        },
        floatingActionButtonPosition = androidx.compose.material3.FabPosition.End,
*/
        ) { paddingValues ->

        Box(modifier = Modifier.fillMaxSize()) {
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

                if (state.isLoading) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = androidx.compose.ui.Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                if (state.error != null) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = androidx.compose.ui.Alignment.Center
                    ) {
                        Text(state.error.asString())
                    }
                }
                if (state.memes.isEmpty()) {
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

            FloatingActionButton(
                onClick = {
                    onAction(MemeListAction.ShowMemePicker)
                },
                modifier = Modifier.padding(16.dp).align(androidx.compose.ui.Alignment.BottomEnd)

            ){
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }

    }

    val skipPartiallyExpanded = false
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = skipPartiallyExpanded)
    AnimatedVisibility(
        visible = state.showMemePicker,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically (
            animationSpec = tween(durationMillis = 1000),
            targetOffsetY = { it })

    ) {

            MemeTemplatePicker(
                viewState = state,
                onTemplateSelected = {
                    onAction(MemeListAction.CreateNewMeme(it))
                },
                onSearch = {
                    onAction(MemeListAction.SearchTemplates(it))
                },
                onDismiss = {
                    onAction(MemeListAction.HideMemePicker)
                }
            )
    }
}