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

    @Query("SELECT * FROM MemeTextEntity WHERE id = :id")
    suspend fun read(id: String): MemeTextEntity?

    @Query("SELECT * FROM MemeTextEntity WHERE imageUri = :imageUri")
    suspend fun readByImageUri(imageUri: String): List<MemeTextEntity>

    @Update
    suspend fun update(memeText: MemeTextEntity)

    @Query("DELETE FROM MemeTextEntity WHERE id = :id")
    suspend fun delete(id: String)

    @Query("SELECT * FROM MemeTextEntity")
    fun readAll(): Flow<List<MemeTextEntity>>

}