package local_storage.room

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import local_storage.StorageInteractor
import local_storage.room.database.MemeDatabase
import local_storage.room.dto.toMeme
import local_storage.room.dto.toMemeEntity
import nl.codingwithlinda.mastermeme.core.domain.model.memes.Meme
import nl.codingwithlinda.mastermeme.core.domain.util.LocalCacheError
import nl.codingwithlinda.mastermeme.core.domain.util.MemeError
import nl.codingwithlinda.mastermeme.core.domain.util.Result

class RoomStorageInteractor(
    database: MemeDatabase
)
    : StorageInteractor<Meme> {

    private val dao = database.memeDao()
    override suspend fun create(item: Meme): Result<Meme, MemeError> {
        dao.create(item.toMemeEntity())
        return Result.Success(item)
    }

    override suspend fun read(id: String): Result<Meme, MemeError> {
        try {
            dao.read(id)?.also {
                return Result.Success(it.toMeme())
            }
        }catch (e: Exception){
            return Result.Error(LocalCacheError.UnknownError)
        }
        return Result.Error(LocalCacheError.MemeNotFound)
    }

    override suspend fun delete(id: String): Result<Unit, MemeError> {
       try {
           val meme = dao.read(id) ?: return Result.Error(LocalCacheError.MemeNotFound)
           dao.delete(id)
           return Result.Success(data = Unit)
       }catch (e: Exception){
           return Result.Error(LocalCacheError.UnknownError)
       }

    }

    override fun readAll(): Flow<List<Meme>> {
        return dao.readAll().map { memeEntities ->
            memeEntities.map { memeEntity ->
                memeEntity.toMeme()
            }
        }
    }

    override suspend fun update(item: Meme): Result<Meme, MemeError> {
       try {
           dao.update(item.toMemeEntity())
           return Result.Success(item)
       }catch (e: Exception){
           return Result.Error(LocalCacheError.UnknownError)
       }
    }

}