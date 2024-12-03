package nl.codingwithlinda.mastermeme.core.presentation.templates

import mastermeme.composeapp.generated.resources.Res
import mastermeme.composeapp.generated.resources.allDrawableResources
import nl.codingwithlinda.mastermeme.core.domain.Templates
import nl.codingwithlinda.mastermeme.core.domain.model.MemeTemplate
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
class TemplatesFromResources : Templates {


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

    override fun getTemplate(id: String): MemeTemplate {
        return Res.allDrawableResources[id]?.let {
            MemeTemplate(
                id = id,
                drawableResource = it
            )
        } ?:  throw IllegalArgumentException("Template with id $id not found")
    }
}