package nl.codingwithlinda.mastermeme.core.presentation.share_application_picker

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.core.app.ShareCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.FileProvider
import java.io.File


actual class ShareAppPicker(
    private val context: Context,
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
    actual fun shareMultiple(imageUris: List<String>) {
        val uris = imageUris.map {
            val file = File(it)
            FileProvider.getUriForFile(context, "nl.codingwithlinda.mastermeme.fileprovider", file)
        }
        val files = ArrayList<Uri>()
        files.addAll(uris)

        val intent = Intent(Intent.ACTION_SEND_MULTIPLE)
            .setType("image/*")
            .putParcelableArrayListExtra(Intent.EXTRA_STREAM, files)
            .setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

        val chooser = Intent.createChooser(intent, "Share File")

        for(uri in uris){
            grantPermission(uri, chooser)
        }

        context.startActivity(chooser)

    }

    private fun grantPermission(uri: Uri, chooser: Intent){
        val resInfoList: List<ResolveInfo> =
            context.getPackageManager()
                .queryIntentActivities(chooser, PackageManager.MATCH_DEFAULT_ONLY)

        for (resolveInfo in resInfoList) {
            val packageName = resolveInfo.activityInfo.packageName
            context.grantUriPermission(
                packageName,
                uri,
                Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
        }
    }
}
