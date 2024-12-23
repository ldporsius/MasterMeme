package nl.codingwithlinda.mastermeme.core.data.local_cache

expect class InternalStorageInteractor {

    //fun write(fileName: String, bytes: ByteArray)
    fun read(fileName: String): ByteArray
}