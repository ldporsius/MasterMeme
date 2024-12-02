package nl.codingwithlinda.mastermeme.core.presentation.templates

import nl.codingwithlinda.mastermeme.core.domain.model.MemeImage
import nl.codingwithlinda.mastermeme.core.domain.model.MemeTemplate
import nl.codingwithlinda.mastermeme.core.presentation.MemeImageUi
import org.jetbrains.compose.resources.DrawableResource

fun MemeTemplate.toUi(
): MemeTemplateUi {
    return MemeTemplateUi(
        id = id,
        image = MemeTemplatesDeclaration.templates.find {
            it.id == id
        }?.image ?: MemeTemplatesDeclaration.emptyTemplate.image,
    )
}

fun MemeTemplate.toUi(
    memeImage: MemeImage<DrawableResource>
): MemeTemplateUi {
    return MemeTemplateUi(
        id = id,
        image = MemeImageUi.pngImage(
            image = memeImage.image(uri)
        )

    )
}