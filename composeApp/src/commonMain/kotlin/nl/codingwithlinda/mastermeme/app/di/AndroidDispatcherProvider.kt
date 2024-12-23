package nl.codingwithlinda.mastermeme.app.di

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlin.coroutines.CoroutineContext

class AndroidDispatcherProvider: DispatcherProvider {
    override val main: CoroutineContext
        get() = Dispatchers.Main
    override val mainImmediate: CoroutineContext
        get() = Dispatchers.Main.immediate
    override val default: CoroutineContext
        get() = Dispatchers.Default
    override val io: CoroutineContext
        get() = Dispatchers.IO
}