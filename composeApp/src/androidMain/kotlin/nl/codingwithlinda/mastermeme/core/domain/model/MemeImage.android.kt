package nl.codingwithlinda.mastermeme.core.domain.model

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri


class MemeImageAsBitmap(
     private val context: Context
 ){

   fun image(uri: String): Bitmap {
        val _uri = Uri.parse(uri)
        context.contentResolver.openInputStream(_uri)?.use {
            val bm = BitmapFactory.decodeStream(it)

            return bm
        }

        return Bitmap.createBitmap(1,1,Bitmap.Config.ARGB_8888)

    }
}