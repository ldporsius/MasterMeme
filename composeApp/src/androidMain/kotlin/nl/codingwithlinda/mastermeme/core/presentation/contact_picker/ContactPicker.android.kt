package nl.codingwithlinda.mastermeme.core.presentation.contact_picker

import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable

actual class ContactPicker(
    private val activity: ComponentActivity
) {
    private lateinit var launcher: ActivityResultLauncher<Void?>

    @Composable
    actual fun registerPicker(onPickContact: (String?) -> Unit) {
        launcher = activity.registerForActivityResult(ActivityResultContracts.PickContact()) {
            it?.let {
                activity.contentResolver?.openInputStream(it)?.use {

                }
            }
        }
    }

    actual fun pickContact() {
        launcher.launch(null)
    }

}