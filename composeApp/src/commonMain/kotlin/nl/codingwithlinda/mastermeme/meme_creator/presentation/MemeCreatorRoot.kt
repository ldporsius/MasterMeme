package nl.codingwithlinda.mastermeme.meme_creator.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import nl.codingwithlinda.mastermeme.core.presentation.templates.TemplatesFromResources
import nl.codingwithlinda.mastermeme.meme_creator.presentation.components.MemeCreatorScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemeCreatorRoot(
    memeId: String,
    onBack: () -> Unit
) {

    val viewModel = MemeCreatorViewModel(
        savedStateHandle = SavedStateHandle().apply {
            set("memeId", memeId)
        },
        templates = TemplatesFromResources()
    )
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "New meme")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBack()
                        },
                        content = {
                            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                        }
                    )
                }
            )
        }
    ){
        paddingValues ->
        MemeCreatorScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            state = viewModel.state.collectAsStateWithLifecycle().value,
            onAction = viewModel::handleAction
        )
    }
}