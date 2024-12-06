package nl.codingwithlinda.mastermeme.core.domain.model.templates


interface MemeTemplates{
    fun getTemplates(): List<MemeTemplate>
    fun getTemplate(id: String): MemeTemplate
}
