package nl.codingwithlinda.mastermeme.core.presentation.templates

import nl.codingwithlinda.mastermeme.core.domain.Templates
import nl.codingwithlinda.mastermeme.core.presentation.MemeImageUi

fun Templates.toUi(): List<MemeTemplateUi> {
   return getTemplates().map {
        MemeTemplateUi(
            id = it.id,
            image = MemeImageUi.pngImage(it.drawableResource)

        )
    }
}
fun memeTemplateToUi(
    id: String,
    templates: Templates
): MemeTemplateUi {
    val image = templates.getTemplates().find {
        it.id == id
    }?.drawableResource
    val imageUi = image?.let {
        MemeImageUi.pngImage(it)
    } ?: MemeTemplatesDeclaration.emptyTemplate.image
    return MemeTemplateUi(
        id = id,
        image = imageUi
    )
}

/*
fun MemeTemplate.toUi(
    memeImage: MemeImage<DrawableResource>
): MemeTemplateUi {
    return MemeTemplateUi(
        id = id,
        image = MemeImageUi.pngImage(
            image = memeImage.image(uri)
        )

    )
}*/
