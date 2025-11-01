package uacj.mx.app08_appra.ui.theme_ui

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Define los estilos de texto para la aplicación, utilizando las fuentes personalizadas.
 */
val AppTypography = Typography(
    // Estilo para el título principal de la pantalla
    headlineLarge = TextStyle(
        fontFamily = SmashFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        letterSpacing = 1.5.sp // Un poco de espacio entre letras le da un buen estilo
    ),
    // Estilo para el nombre de cada pista
    titleMedium = TextStyle(
        fontFamily = BodyFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    // Estilo para el texto de los botones
    labelLarge = TextStyle(
        fontFamily = SmashFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    // Estilo para el cuerpo de texto general (si lo necesitas)
    bodyLarge = TextStyle(
        fontFamily = BodyFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)