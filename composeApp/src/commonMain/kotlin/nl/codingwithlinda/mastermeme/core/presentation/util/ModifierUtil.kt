package nl.codingwithlinda.mastermeme.core.presentation.util

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout


fun Modifier.printConstraints(tag: String): Modifier {
    return layout { measurable, constraints ->
        println("$tag: $constraints")
        val placeable = measurable.measure(
            constraints = constraints
        )

        layout(placeable.width, placeable.height) {
            placeable.place(0, 0)
        }
    }
}

fun Modifier.applyIf(
    condition: Boolean,
    modifier: Modifier.() -> Modifier): Modifier {
    return if (condition) {
        this.then(modifier())
    } else {
        this
    }
}