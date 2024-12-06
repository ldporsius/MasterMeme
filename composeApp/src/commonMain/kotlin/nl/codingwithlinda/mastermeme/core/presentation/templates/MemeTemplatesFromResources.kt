package nl.codingwithlinda.mastermeme.core.presentation.templates

import nl.codingwithlinda.mastermeme.core.domain.model.templates.MemeTemplate
import nl.codingwithlinda.mastermeme.core.domain.model.templates.MemeTemplates
import nl.codingwithlinda.mastermeme.core.domain.model.templates.emptyMemeTemplate
import nl.codingwithlinda.mastermeme.core.domain.model.templates.templates

class MemeTemplatesFromResources : MemeTemplates {

    override fun getTemplates(): List<MemeTemplate> {
        return templates
    }

    override fun getTemplate(id: String): MemeTemplate {
       return templates.find { it.id == id } ?: emptyMemeTemplate
    }
}