package nl.codingwithlinda.mastermeme.core.presentation.share_application_picker

import androidx.compose.runtime.Composable

expect class ShareAppPicker{

    @Composable
    fun registerPicker(onShare: (String) -> Unit)

    fun share(imageUri: String)


}