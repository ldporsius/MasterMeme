package nl.codingwithlinda.mastermeme.memes_list.data

import kotlinx.coroutines.flow.Flow
import nl.codingwithlinda.mastermeme.core.data.local_storage.StorageInteractor
import nl.codingwithlinda.mastermeme.core.domain.model.memes.Meme
import nl.codingwithlinda.mastermeme.core.domain.util.Result
import nl.codingwithlinda.mastermeme.memes_list.domain.MemeListRepository

class MemeListRepoImpl(
    private val storageInteractor: StorageInteractor<Meme>
): MemeListRepository  {
    override fun getMemes(): Flow<List<Meme>> {
        return storageInteractor.readAll()
    }

    override suspend fun toggleFavorite(memeId: String){
        storageInteractor.read(memeId).let {
            when(it){
                is Result.Error -> {
                    println("Error toggling favorite")
                }
                is Result.Success -> {
                    val update = it.data.copy(
                        isFavorite = !it.data.isFavorite
                    )
                    val res = storageInteractor.update(update)
                    when(res){
                        is Result.Error -> {
                            println("Error toggling favorite")
                        }
                        is Result.Success -> {
                            println("Success toggling favorite ${res.data.isFavorite}")
                        }
                    }
                }
            }
        }
    }

    override suspend fun deleteMeme(memeId: String) {
        storageInteractor.delete(memeId)
    }

}