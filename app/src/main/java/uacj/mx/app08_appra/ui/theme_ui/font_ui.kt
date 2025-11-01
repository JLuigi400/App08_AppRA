package uacj.mx.app08_appra.ui.theme_ui

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import uacj.mx.app08_appra.R

/**
 * Define las familias de fuentes personalizadas para la aplicación,
 * inspiradas en la estética de Super Smash Bros.
 */
val SmashFontFamily = FontFamily(
    // Fuente para los títulos y encabezados. Es audaz y condensada.
    // Reemplaza 'bebas_neue_regular' con el nombre de tu archivo de fuente en res/font.
    Font(R.font.bebas_neue_regular, FontWeight.Normal),
    Font(R.font.bebas_neue_regular, FontWeight.Bold) // Puedes usar la misma si no tienes una versión bold.
)

val BodyFontFamily = FontFamily(
    // Fuente para el cuerpo del texto, fácil de leer.
    // Reemplaza 'roboto_regular' y 'roboto_bold' con tus archivos de fuente.
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_bold, FontWeight.Bold)
)