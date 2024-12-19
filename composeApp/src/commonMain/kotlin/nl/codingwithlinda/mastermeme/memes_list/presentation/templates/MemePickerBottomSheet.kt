package nl.codingwithlinda.mastermeme.memes_list.presentation.templates

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import nl.codingwithlinda.mastermeme.memes_list.presentation.state.MemeListAction
import nl.codingwithlinda.mastermeme.memes_list.presentation.state.MemeListViewState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemePickerBottomSheet(
    state: MemeListViewState,
    onAction: (MemeListAction) -> Unit,
) {

    val skipPartiallyExpanded = false
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = skipPartiallyExpanded)

    ModalBottomSheet(
        onDismissRequest = {
            onAction(MemeListAction.HideMemePicker)
        },
        sheetState = bottomSheetState,
        sheetMaxWidth = Dp.Infinity
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