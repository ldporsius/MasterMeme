package nl.codingwithlinda.mastermeme.core.presentation.templates

import mastermeme.composeapp.generated.resources.Res
import mastermeme.composeapp.generated.resources.allDrawableResources
import nl.codingwithlinda.mastermeme.core.domain.Templates
import nl.codingwithlinda.mastermeme.core.domain.model.MemeTemplate
import org.jetbrains.compose.resources.ExperimentalResourceApi

class TemplatesFromResources : Templates {

    @OptIn(ExperimentalResourceApi::class)
    override fun getTemplates(): List<MemeTemplate> {
        return Res.allDrawableResources
            .filterNot {
                it.key.startsWith("vector")
            }
            .map {
            MemeTemplate(
                id = it.key,
                drawableResource = it.value
            )
        }
    }
}