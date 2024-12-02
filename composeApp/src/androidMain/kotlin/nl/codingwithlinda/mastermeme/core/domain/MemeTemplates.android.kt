package nl.codingwithlinda.mastermeme.core.domain

import mastermeme.composeapp.generated.resources.Res
import mastermeme.composeapp.generated.resources.allDrawableResources
import nl.codingwithlinda.mastermeme.core.domain.model.MemeTemplate
import org.jetbrains.compose.resources.ExperimentalResourceApi

class TemplatesImpl() : Templates{

    @OptIn(ExperimentalResourceApi::class)
    override fun getTemplates(): List<MemeTemplate> {
        return Res.allDrawableResources.map {
            MemeTemplate(
                id = it.key,
                uri = it.value.toString()
            )
        }

    }
}