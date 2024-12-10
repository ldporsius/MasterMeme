package nl.codingwithlinda.mastermeme.core.data.local_cache

import androidx.room.RoomDatabase
import nl.codingwithlinda.mastermeme.core.data.local_storage.room.database.MemeDatabase

actual class DatabaseFactory {
    actual fun create(): RoomDatabase.Builder<MemeDatabase> {
        TODO("Not yet implemented")
    }
}