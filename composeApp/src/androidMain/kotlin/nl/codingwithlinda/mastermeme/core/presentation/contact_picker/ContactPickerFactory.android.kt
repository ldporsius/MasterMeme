package nl.codingwithlinda.mastermeme.core.presentation.contact_picker

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

actual class ContactPickerFactory{
    @Composable
    actual fun create(): ContactPicker {
        val activity: ComponentActivity = LocalContext.current as ComponentActivity
        return remember(activity) {
            ContactPicker(activity)
        }

    }

}