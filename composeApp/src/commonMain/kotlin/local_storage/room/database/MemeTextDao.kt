package local_storage.room.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import local_storage.room.model.MemeEntity
import local_storage.room.model.MemeTextEntity

@Dao
interface MemeTextDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(memeText: MemeTextEntity)

    @Query("SELECT * FROM MemeTextEntity WHERE primaryKey = :id")
    suspend fun read(id: Int): MemeTextEntity?

    @Query("SELECT * FROM MemeTextEntity WHERE memeId  = :memeId")
    suspend fun readByMemeId(memeId: String): List<MemeTextEntity>

    @Update
    suspend fun update(memeText: MemeTextEntity)

    @Query("DELETE FROM MemeTextEntity WHERE memeId = :memeId")
    suspend fun delete(memeId: String)

    @Query("SELECT * FROM MemeTextEntity")
    fun readAll(): Flow<List<MemeTextEntity>>

}