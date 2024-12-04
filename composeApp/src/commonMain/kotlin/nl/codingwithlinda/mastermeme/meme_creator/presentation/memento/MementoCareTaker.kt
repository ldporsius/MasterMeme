package nl.codingwithlinda.mastermeme.meme_creator.presentation.memento

import nl.codingwithlinda.mastermeme.core.domain.Memento

class MementoCareTaker<T>(
    private val originator: T
) {
    private val history: MutableList<Memento<T>> = mutableListOf()
    fun saveState(state: Memento<T>) {
        history.add(state)
    }
    fun undo(): T?{
        val previousState = history.removeLastOrNull()
        return previousState?.restoreState()

    }
}