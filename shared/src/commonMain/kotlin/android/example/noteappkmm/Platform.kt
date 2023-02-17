package android.example.noteappkmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform