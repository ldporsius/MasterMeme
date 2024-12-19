package nl.codingwithlinda.mastermeme.memes_list.presentation.templates

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import nl.codingwithlinda.mastermeme.memes_list.presentation.state.MemeListAction
import nl.codingwithlinda.mastermeme.memes_list.presentation.state.MemeListViewState

@Composable
fun MemePickerAnimatedVis(
    state: MemeListViewState,
    onAction: (MemeListAction) -> Unit,
) {
    AnimatedVisibility(
        visible = false,
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