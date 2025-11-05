package uacj.mx.app08_appra.ui.pantallas

import android.location.Location
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uacj.mx.app08_appra.modelos.Pista
import uacj.mx.app08_appra.repositorios.estaticos.RepositorioPrueba
import uacj.mx.app08_appra.ui.theme_ui.* // Importa todo desde tu paquete de tema
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Map
import uacj.mx.app08_appra.modelos.TiposDePistas
import uacj.mx.app08_appra.modelos.informacion
import uacj.mx.app08_appra.ui.organismos.InformacionVista

@Composable
fun Principal(ubicacion: Location?, modificador: Modifier = Modifier) {
    // Envolvemos toda la pantalla en nuestro tema personalizado.
    // Esto aplica automáticamente los colores y fuentes correctos.
    AppTheme {

        Column (modificador) {
            for(pista in RepositorioPrueba.pista){

                if(ubicacion == null){
                    continue
                }
                if(ubicacion.distanceTo(pista.ubicacion) < pista.distancia_maxima){

                    Text("La Pista es: ${pista.nombre}")
                    Text("La distancia a la pista es: ${ubicacion?.distanceTo(pista.ubicacion)}")

                    when(pista.cuerpo.tipo){
                        TiposDePistas.texto -> {
                            InformacionVista(pista.cuerpo as informacion)
                        }
                        TiposDePistas.interactivo -> {
                            InformacionVista(pista.cuerpo as informacion)
                        }
                        TiposDePistas.camara -> {
                            TODO()
                        }
                        TiposDePistas.agitar_telefono -> {
                            TODO()
                        }
                    }
                }
            }
        }

        // Usamos Scaffold para una estructura de pantalla limpia

        Scaffold(
            containerColor = MaterialTheme.colorScheme.background, // Usa el color de fondo del tema
            topBar = {
                Text(
                    text = "GESTOR DE PISTAS",
                    style = MaterialTheme.typography.headlineLarge, // Usa el estilo de título del tema
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp)
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            },
            bottomBar = {
                AppBottomNavigationBar()
            }
        ) { innerPadding ->
            // LazyColumn es más eficiente que un 'for' dentro de un Column para listas.
            LazyColumn(
                modifier = modificador
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp) // Espacio automático entre items
            ) {
            }
        }

    }
}

@Composable
fun AppBottomNavigationBar(modifier: Modifier = Modifier) {
    // NavigationBar es el componente de Material 3 para menús inferiores.
    NavigationBar(
        modifier = modifier,
        containerColor = SmashFrameBackground, // Usa el color de fondo de las tarjetas
        contentColor = SmashLightGray         // Color por defecto para iconos y texto inactivos
    ) {
        // --- Elemento 1: Inicio ---
        NavigationBarItem(
            selected = false, // 'selected' controla el estado visual (color, etc.)
            onClick = { /* TODO: Acción para navegar a Inicio */ },
            icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
            label = { Text("Inicio") },
            colors = NavigationBarItemDefaults.colors(
                unselectedIconColor = SmashLightGray,
                unselectedTextColor = SmashLightGray,
                indicatorColor = SmashRed // Color del indicador cuando está seleccionado
            )
        )

        // --- Elemento 2: Pistas ---
        NavigationBarItem(
            selected = true, // Marcamos "Pistas" como la pantalla actual
            onClick = { /* Ya estamos aquí */ },
            icon = { Icon(Icons.Default.List, contentDescription = "Pistas") },
            label = { Text("Pistas") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = SmashWhite,    // Color del icono activo
                selectedTextColor = SmashWhite,    // Color del texto activo
                unselectedIconColor = SmashLightGray,
                unselectedTextColor = SmashLightGray,
                indicatorColor = SmashRed          // El "círculo" de fondo del ítem activo
            )
        )

        // --- Elemento 3: Mapa ---
        NavigationBarItem(
            selected = false,
            onClick = { /* TODO: Acción para navegar a Mapa */ },
            icon = { Icon(Icons.Default.Map, contentDescription = "Mapa") },
            label = { Text("Mapa") },
            colors = NavigationBarItemDefaults.colors(
                unselectedIconColor = SmashLightGray,
                unselectedTextColor = SmashLightGray,
                indicatorColor = SmashRed
            )
        )
    }
}

@Composable
fun TrackItem(pista: Pista, ubicacionActual: Location) {
    // Card ya usa los colores de 'surface' de nuestro tema
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Fila superior con el nombre y botones
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = pista.nombre,
                    style = MaterialTheme.typography.titleMedium, // Estilo para el nombre de la pista
                    modifier = Modifier.weight(1f)
                )
                // Botones de acción
                Row {
                    ActionButton(text = "Editar", color = SmashRed)
                    Spacer(modifier = Modifier.width(8.dp))
                    ActionButton(text = "X", color = SmashDestructiveGray)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Información adicional de la pista
            Text(
                text = "Distancia: ${ubicacionActual.distanceTo(pista.ubicacion).toInt()} metros",
                style = MaterialTheme.typography.bodyLarge,
                color = SmashDarkBackground // Un color secundario para no competir con el título
            )

            // Aquí puedes añadir más detalles basados en el tipo de pista si quieres
            // when (pista.cuerpo.tipo) { ... }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun PrincipalPreview() {
    Principal(
        ubicacion = TODO(),
        modificador = TODO()
    )
}
