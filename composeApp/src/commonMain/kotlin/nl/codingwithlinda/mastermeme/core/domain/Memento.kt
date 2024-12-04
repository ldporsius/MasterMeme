package nl.codingwithlinda.mastermeme.core.domain

interface Memento<T> {
    fun restoreState(): T?
}