package org.example

enum class ColorPlayer{
    WHITE,BLACK;
    fun next(): ColorPlayer {
        val values = entries
        return values[(ordinal + 1) % values.size]
    }
}
