package nl.codingwithlinda.mastermeme.core.presentation.share_application_picker

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

actual class ShareAppPickerFactory() {
    @Composable
    actual fun create(): ShareAppPicker {
        val activity: ComponentActivity = LocalContext.current as ComponentActivity
        return remember(activity) {
            ShareAppPicker(activity)
        }
    }

}