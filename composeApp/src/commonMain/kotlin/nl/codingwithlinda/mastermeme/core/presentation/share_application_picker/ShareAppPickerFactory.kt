package nl.codingwithlinda.mastermeme.core.presentation.share_application_picker

import androidx.compose.runtime.Composable

expect class ShareAppPickerFactory {
    @Composable
    fun create(): ShareAppPicker

}