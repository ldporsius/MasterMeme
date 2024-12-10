package nl.codingwithlinda.mastermeme.core.presentation.util

actual class DateTimeUtils {
    actual fun getSystemTimeMillis(): Long {
        return System.currentTimeMillis()
    }
}