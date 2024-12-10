package nl.codingwithlinda.mastermeme.core.data.local_cache

import androidx.room.RoomDatabase
import nl.codingwithlinda.mastermeme.core.data.local_storage.room.database.MemeDatabase

expect class DatabaseFactory {
    fun create(): RoomDatabase.Builder<MemeDatabase>
}