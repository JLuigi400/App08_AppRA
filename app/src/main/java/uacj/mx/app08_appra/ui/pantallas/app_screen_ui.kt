package uacj.mx.app08_appra.ui.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uacj.mx.app08_appra.ui.theme_ui.* // Importa tus colores y tipografía

@Composable
fun AppScreen() {
    // Datos de ejemplo para las pistas
    val tracks = kotlin.collections.listOf(
        "Pista de Audio 01 - Final Destination",
        "Pista MIDI 02 - Hyrule Castle",
        "Pista de Efectos - Falcon Punch FX",
        "Pista de Ambiente - Fountain of Dreams"
    )

    // Usamos un Scaffold para la estructura básica de la pantalla
    Scaffold(
        containerColor = SmashDarkBackground, // Color de fondo principal
        topBar = {
            Text(
                text = "GESTOR DE PISTAS",
                style = AppTypography.headlineLarge,
                color = SmashWhite,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally) // Centra el texto
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            items(tracks) { trackName ->
                TrackItem(trackName = trackName)
                Spacer(modifier = Modifier.height(8.dp)) // Espacio entre cada pista
            }
        }
    }
}

@Composable
fun TrackItem(trackName: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = SmashFrameBackground // Fondo para la tarjeta de la pista
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Nombre de la pista
            Text(
                text = trackName,
                style = AppTypography.titleMedium,
                color = SmashWhite,
                modifier = Modifier.weight(1f) // Ocupa el espacio disponible
            )

            // Botones de acción (sin funcionalidad)
            Row {
                ActionButton(text = "Editar", color = SmashRed)
                Spacer(modifier = Modifier.width(8.dp))
                ActionButton(text = "X", color = SmashDestructiveGray)
            }
        }
    }
}

@Composable
fun ActionButton(text: String, color: Color) {
    Button(
        onClick = { /* Acción no implementada */ },
        shape = MaterialTheme.shapes.small,
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = SmashWhite
        ),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Text(text = text, style = AppTypography.labelLarge)
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun AppScreenPreview() {
    // Para la previsualización, es útil envolverlo en un tema
    // si no lo tienes configurado globalmente aún.
    MaterialTheme(typography = AppTypography) {
        AppScreen()
    }
}