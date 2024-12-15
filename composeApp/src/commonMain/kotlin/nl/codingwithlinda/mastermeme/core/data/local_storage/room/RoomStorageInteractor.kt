package nl.codingwithlinda.mastermeme.core.data.local_storage.room

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import nl.codingwithlinda.mastermeme.core.data.local_storage.StorageInteractor
import nl.codingwithlinda.mastermeme.core.data.local_storage.room.database.MemeDatabase
import nl.codingwithlinda.mastermeme.core.data.local_storage.room.dto.toDomain
import nl.codingwithlinda.mastermeme.core.data.local_storage.room.dto.toMeme
import nl.codingwithlinda.mastermeme.core.data.local_storage.room.dto.toMemeEntity
import nl.codingwithlinda.mastermeme.core.data.local_storage.room.dto.toMemeTextEntity
import nl.codingwithlinda.mastermeme.core.domain.model.memes.Meme
import nl.codingwithlinda.mastermeme.core.domain.util.LocalCacheError
import nl.codingwithlinda.mastermeme.core.domain.util.MemeError
import nl.codingwithlinda.mastermeme.core.domain.util.Result

class RoomStorageInteractor(
    database: MemeDatabase
) : StorageInteractor<Meme> {

    private val dao = database.memeDao()
    private val textDao = database.memeTextDao()

    override suspend fun create(item: Meme): Result<Meme, MemeError> {
        dao.create(item.toMemeEntity())
        item.texts.forEach { memeText ->
            textDao.create(memeText.toMemeTextEntity(
                memeId = item.id,
                imageUri = item.imageUri))
        }
        return Result.Success(item)
    }

    override suspend fun read(id: String): Result<Meme, MemeError> {
        try {
            dao.read(id)?.also {
                val texts = textDao.readByMemeId(it.id).map {
                    it.toDomain()
                }
                return Result.Success(it.toMeme(texts))
            }
        }catch (e: Exception){
            return Result.Error(LocalCacheError.UnknownError)
        }
        return Result.Error(LocalCacheError.MemeNotFound)
    }

    override suspend fun delete(id: String): Result<Unit, MemeError> {
       try {
           dao.delete(id)
           return Result.Success(data = Unit)
       }catch (e: Exception){
           return Result.Error(LocalCacheError.UnknownError)
       }

    }

    override fun readAll(): Flow<List<Meme>> {
        return dao.readAll().map { memeEntities ->
            memeEntities.map { memeEntity ->
                val texts = textDao.readByMemeId(memeEntity.id).map {
                    it.toDomain()
                }
                memeEntity.toMeme(texts)
            }
        }
    }

    override suspend fun update(item: Meme): Result<Meme, MemeError> {
       try {
           val texts = item.texts.map {
               it.toMemeTextEntity(
                   memeId = item.id,
                   imageUri = item.imageUri)
           }
           textDao.delete(item.id)
           texts.forEach {
               textDao.create(it)
           }

           dao.update(item.toMemeEntity())
           return Result.Success(item)
       }catch (e: Exception){
           return Result.Error(LocalCacheError.UnknownError)
       }
    }



}