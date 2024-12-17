package nl.codingwithlinda.mastermeme.core.presentation.templates

import nl.codingwithlinda.mastermeme.core.domain.model.templates.MemeTemplates
import nl.codingwithlinda.mastermeme.core.domain.model.templates.templateToBytes
import nl.codingwithlinda.mastermeme.core.presentation.model.MemeImageUi
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.decodeToImageBitmap

@OptIn(ExperimentalResourceApi::class)
suspend fun MemeTemplates.toUi(): List<MemeTemplateUi> {

   return getTemplates().map {
       val bm = templateToBytes(it.drawableResource).decodeToImageBitmap()
       MemeTemplateUi(
           id = it.id,
           image = MemeImageUi.bitmapImage(bm),
           name = it.name
       )
    }
}

