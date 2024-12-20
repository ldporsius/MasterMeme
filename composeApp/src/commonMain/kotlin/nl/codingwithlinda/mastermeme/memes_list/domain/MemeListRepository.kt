package nl.codingwithlinda.mastermeme.memes_list.domain

import kotlinx.coroutines.flow.Flow
import nl.codingwithlinda.mastermeme.core.domain.model.memes.Meme

interface MemeListRepository {

    fun getMemes(): Flow<List<Meme>>
    suspend fun toggleFavorite(memeId: String)
    suspend fun deleteMeme(memeId: String)

}