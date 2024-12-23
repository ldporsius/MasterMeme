package nl.codingwithlinda.mastermeme.app.di

import kotlin.coroutines.CoroutineContext

interface DispatcherProvider {
    val main: CoroutineContext
    val mainImmediate: CoroutineContext
    val default: CoroutineContext
    val io: CoroutineContext
}