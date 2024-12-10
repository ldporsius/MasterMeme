package local_storage.room.database

import androidx.room.Room
import androidx.room.RoomDatabase

actual class DatabaseFactory(

) {
    actual fun create(): RoomDatabase.Builder<MemeDatabase> {


    }
}