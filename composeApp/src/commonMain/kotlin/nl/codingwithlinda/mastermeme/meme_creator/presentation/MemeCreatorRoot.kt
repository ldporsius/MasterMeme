package nl.codingwithlinda.mastermeme.meme_creator.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import nl.codingwithlinda.mastermeme.core.data.local_storage.StorageInteractor
import nl.codingwithlinda.mastermeme.core.domain.model.memes.Meme
import nl.codingwithlinda.mastermeme.core.presentation.create_meme.ColorPicker
import nl.codingwithlinda.mastermeme.core.presentation.create_meme.FontPicker
import nl.codingwithlinda.mastermeme.core.presentation.share_application_picker.ImageConverter
import nl.codingwithlinda.mastermeme.core.presentation.share_application_picker.ShareAppPicker
import nl.codingwithlinda.mastermeme.core.presentation.templates.MemeTemplatesFromResources
import nl.codingwithlinda.mastermeme.meme_creator.domain.MemeFactory
import nl.codingwithlinda.mastermeme.meme_creator.presentation.components.MemeCreatorScreen
import nl.codingwithlinda.mastermeme.meme_creator.presentation.components.confirm_exit.BackHandler
import nl.codingwithlinda.mastermeme.meme_creator.presentation.components.confirm_exit.ConfirmExitDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemeCreatorRoot(
    memeId: String,
    shareAppPicker: ShareAppPicker,
    imageConverter: ImageConverter,
    colorPicker: ColorPicker,
    fontPicker: FontPicker,
    storageInteractor: StorageInteractor<Meme>,
    memeFactory: MemeFactory,
    onBack: () -> Unit,
) {

    var showConfirmExit by remember {
        mutableStateOf(false)
    }
    var shouldShowConfirmExit by remember {
        mutableStateOf(false)
    }

    val viewModel = MemeCreatorViewModel(
        savedStateHandle = SavedStateHandle().apply {
            set("memeId", memeId)
        },
        memeTemplates = MemeTemplatesFromResources(),
        imageConverter = imageConverter,
        fontPicker = fontPicker,
        storageInteractor = storageInteractor,
        memeFactory = memeFactory,
        shouldShowConfirmExit = {
            shouldShowConfirmExit = it
            println("MEME CREATOR ROOT. shouldShowConfirmExit: $shouldShowConfirmExit")
        }
        )


    BackHandler(
        enabled = true,
        onBackPressed = {
            if (shouldShowConfirmExit)
            showConfirmExit = true
            else
                onBack()
        }
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
                            if (shouldShowConfirmExit)
                                showConfirmExit = true
                            else
                                onBack()
                        },
                        content = {
                            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                        }
                    )
                }
            )
        }
    ){ paddingValues ->

        if(showConfirmExit){
            ConfirmExitDialog(
                onDismiss = {
                    showConfirmExit = false
                },
                onConfirm = {
                    onBack()
                }
            )
        }

        MemeCreatorScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .systemBarsPadding()
                .safeContentPadding()

            ,
            state = viewModel.state.collectAsStateWithLifecycle().value,
            colors = colorPicker.colors,
            shareAppPicker = shareAppPicker,
            fonts = fontPicker.fontResources,
            onAction = {
                viewModel.handleAction(it)
            },
        )
    }
}