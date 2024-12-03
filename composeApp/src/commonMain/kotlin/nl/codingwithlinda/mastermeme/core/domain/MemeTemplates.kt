package nl.codingwithlinda.mastermeme.core.domain

import nl.codingwithlinda.mastermeme.core.domain.model.MemeTemplate


interface Templates{
    fun getTemplates(): List<MemeTemplate>
    fun getTemplate(id: String): MemeTemplate
}
