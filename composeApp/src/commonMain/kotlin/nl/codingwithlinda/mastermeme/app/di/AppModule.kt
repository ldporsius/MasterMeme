package nl.codingwithlinda.mastermeme.app.di

import nl.codingwithlinda.mastermeme.core.domain.local_cache.LocalCache

interface AppModule {

    fun localCache(): LocalCache
}