package uacj.mx.app08_appra.ui.theme_ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme // O darkColorScheme si prefieres
import androidx.compose.runtime.Composable

// Define la paleta de colores para el tema de la app.
// Usamos los colores que creaste en Color.kt
private val AppColorScheme = lightColorScheme(
    primary = SmashRed,
    background = SmashDarkBackground,
    surface = SmashFrameBackground,
    onPrimary = SmashWhite,
    onBackground = SmashWhite,
    onSurface = SmashWhite
    // Puedes definir más colores si los necesitas
)

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = AppColorScheme,
        typography = AppTypography, // La tipografía que definiste en Typography.kt
        content = content
    )
}