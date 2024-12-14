package nl.codingwithlinda.mastermeme.core.presentation.share_application_picker

import android.content.Context
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.core.app.ShareCompat
import androidx.core.content.FileProvider
import java.io.File

actual class ShareAppPicker(
    private val context: Context
) {

    private lateinit var launcher: ActivityResultLauncher<Intent>

    @Composable
    actual fun registerPicker(onShare: (String) -> Unit) {
        launcher =
            rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                it.data?.let {
                    println("Data: ${it.data}")
                }
            }
    }

    actual fun share(imageUri: String) {

        val file = File(imageUri)

        val uri =
            FileProvider.getUriForFile(context, "nl.codingwithlinda.mastermeme.fileprovider", file)

        println("SHARE APP PICKER HAS URI: ${uri}")

        ShareCompat.IntentBuilder(context)
            .setType("image/*")
            .setSubject("Shared meme")
            .addStream(uri)
            .setChooserTitle("Shared meme")
            .startChooser()

    }
}
