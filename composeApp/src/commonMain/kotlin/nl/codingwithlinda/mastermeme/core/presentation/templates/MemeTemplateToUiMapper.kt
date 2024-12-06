package nl.codingwithlinda.mastermeme.core.presentation.templates

import nl.codingwithlinda.mastermeme.core.domain.model.templates.MemeTemplates
import nl.codingwithlinda.mastermeme.core.presentation.model.MemeImageUi

fun MemeTemplates.toUi(): List<MemeTemplateUi> {
   return getTemplates().map {
       MemeTemplateUi(
           id = it.id,
           image = MemeImageUi.pngImage(it.drawableResource)
       )

    }
}

