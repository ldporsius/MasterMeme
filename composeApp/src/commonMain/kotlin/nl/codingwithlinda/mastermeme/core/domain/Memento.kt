package nl.codingwithlinda.mastermeme.core.domain

interface Memento<T> {

    //fun saveState(value: T)
    fun restoreState(): T?
    //fun takeSnapshot(value: T): Memento<T>
}