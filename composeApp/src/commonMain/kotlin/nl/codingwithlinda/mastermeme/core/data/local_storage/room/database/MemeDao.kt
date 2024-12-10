package nl.codingwithlinda.mastermeme.core.data.local_storage.room.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import nl.codingwithlinda.mastermeme.core.data.local_storage.room.model.MemeEntity

@Dao
interface MemeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(meme: MemeEntity)

    @Query("SELECT * FROM MemeEntity WHERE id = :id")
    suspend fun read(id: String): MemeEntity?

    @Update
    suspend fun update(meme: MemeEntity)

    @Query("DELETE FROM MemeEntity WHERE id = :id")
    suspend fun delete(id: String)

    @Query("SELECT * FROM MemeEntity")
    fun readAll(): Flow<List<MemeEntity>>

}