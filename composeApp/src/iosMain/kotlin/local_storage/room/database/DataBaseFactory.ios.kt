package local_storage.room.database

import androidx.room.RoomDatabase
import nl.codingwithlinda.mastermeme.core.data.local_storage.room.database.MemeDatabase

actual class DatabaseFactory(

) {
    actual fun create(): RoomDatabase.Builder<MemeDatabase> {


    }
}