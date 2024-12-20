package nl.codingwithlinda.mastermeme.core.domain.model.templates


interface MemeTemplatesProvider{
    fun getTemplates(): List<MemeTemplate>
    fun getTemplate(id: String): MemeTemplate
}
