package nl.codingwithlinda.mastermeme

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform