package uacj.mx.app08_appra.ui.pantallas

import android.location.Location
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import uacj.mx.app08_appra.modelos.InformacionInteractiva
import uacj.mx.app08_appra.modelos.TiposDePistas
import uacj.mx.app08_appra.modelos.informacion
import uacj.mx.app08_appra.repositorios.estaticos.RepositorioPrueba

@Composable
fun Principal(modificador: Modifier = Modifier){
    val ubicacion = Location("Proveedor").apply{
        latitude = 31.7156044
        longitude = -106.4246012
    }

    Column (modificador) {
        for(pista in RepositorioPrueba.pista){
            Text("La pista es: ${pista.nombre}")
            Text("La direccion a la pista es: ${ubicacion.distanceTo(pista.ubicacion)}")

            when(pista.cuerpo.tipo){
                TiposDePistas.texto -> {
                    Text("El texto de la pista es: ${pista.cuerpo as informacion}")
                }

                TiposDePistas.interactivo -> {
                    Text("El texto de la pista es: ${pista.cuerpo as InformacionInteractiva}")
                }

                TiposDePistas.camara -> {
                    Text("El texto de la pista es: ${pista.cuerpo as informacion}")
                }

                TiposDePistas.agitar_telefono -> {
                    TODO()
                }
            }
            Text("----------------")
        }
    }
}