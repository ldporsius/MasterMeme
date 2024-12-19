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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.mastermeme.memes_list.presentation.components.EmptyListComponent
import nl.codingwithlinda.mastermeme.memes_list.presentation.home_screen.top_bar.SortMemesTopBar
import nl.codingwithlinda.mastermeme.memes_list.presentation.state.MemeListAction
import nl.codingwithlinda.mastermeme.memes_list.presentation.state.MemeListViewState
import nl.codingwithlinda.mastermeme.memes_list.presentation.templates.MemePickerBottomSheet
import nl.codingwithlinda.mastermeme.memes_list.presentation.templates.MemeTemplatePicker

@Composable
fun MemeListScreen(
    state: MemeListViewState,
    isSelecting: Boolean,
    isSelected: (memeId: String) -> Boolean,
    toggleMemeSelection: (memeId: String) -> Unit,
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

        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {

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
                    isSelecting = isSelecting,
                    isSelected = isSelected,
                    toggleMemeSelection = toggleMemeSelection,
                    onMemeClick = {
                        onAction(MemeListAction.MemeClicked(it))
                    },
                    onMemeLongPress = {
                        onAction(MemeListAction.MemeLongPressed(it))
                    }
                )
            }
        }
    }

    if (state.showMemePicker) {
        MemePickerBottomSheet(
            state = state,
            onAction = onAction
        )
    }

}