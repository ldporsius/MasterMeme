package nl.codingwithlinda.mastermeme.meme_creator.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import nl.codingwithlinda.mastermeme.core.presentation.MemeTemplatesImpl
import nl.codingwithlinda.mastermeme.meme_creator.presentation.components.MemeCreatorScreen
import nl.codingwithlinda.mastermeme.meme_creator.presentation.state.MemeCreatorViewState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemeCreatorRoot(
    onBack: () -> Unit
) {

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
            modifier = Modifier.padding(paddingValues),
            state = MemeCreatorViewState(
                memeImageUi = MemeTemplatesImpl.getTemplates().first()
            )
        )

    }
}