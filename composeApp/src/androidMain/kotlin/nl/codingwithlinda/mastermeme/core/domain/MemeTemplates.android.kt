package nl.codingwithlinda.mastermeme.core.domain

import mastermeme.composeapp.generated.resources.Res
import mastermeme.composeapp.generated.resources.allDrawableResources
import nl.codingwithlinda.mastermeme.R
import nl.codingwithlinda.mastermeme.core.domain.model.MemeTemplate
import org.jetbrains.compose.resources.ExperimentalResourceApi

class TemplatesImpl() : Templates{

    val t0 = R.drawable.template_0_1otri4

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