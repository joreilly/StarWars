package dev.johnoreilly.starwars.wearApp.theme

import androidx.compose.runtime.Composable
import androidx.wear.compose.material.Colors
import androidx.wear.compose.material.MaterialTheme

private val DarkColorPalette = Colors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

@Composable
fun StarWarsTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = DarkColorPalette,
        content = content
    )
}