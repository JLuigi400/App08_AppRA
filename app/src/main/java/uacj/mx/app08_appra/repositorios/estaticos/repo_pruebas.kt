package uacj.mx.app08_appra.repositorios.estaticos

import android.location.Location
import androidx.collection.objectListOf
import uacj.mx.app08_appra.modelos.Boton
import uacj.mx.app08_appra.modelos.InformacionInteractiva
import uacj.mx.app08_appra.modelos.Pista
import uacj.mx.app08_appra.modelos.informacion
import uacj.mx.app08_appra.ui.organismos.InformacionVista

object RepositorioPrueba{
    var pista = listOf(Pista(
        nombre = "Pista 01",
        ubicacion = Location("Proveedor").apply{
            latitude = 31.742609
            longitude = -106.432703
        },
        cuerpo = informacion(
            texto = "Prueba de texto para comprobar pista 1",
            imagen = null
        )
    ),
        Pista(
            nombre = "Pista 02",
            ubicacion = Location("Proveedor").apply{
                latitude = 31.7429201
                longitude = -106.4336828
            },
            cuerpo = informacion(
                texto = "Este es el texto de la pista 02",
                imagen = null
            ),
        ),
        Pista(
            nombre = "Pista 03",
            ubicacion = Location("Proveedor").apply{
                latitude = 31.743057
                longitude = -106.4330017
            },
            cuerpo = informacion(
                texto = "Este es el texto de la pista 03",
                imagen = null
            ),
        ),
        Pista(
            nombre = "Pista 04",
            ubicacion = Location("Proveedor"),
            cuerpo = InformacionInteractiva(
                texto = "Este es el texto de la pista 04 tipo interactivo",
                lista_botones = listOf(
                    Boton(
                        texto = "Ir a pantalla 01",
                        direccion = null
                    ),
                    Boton(
                        texto = "Ir a pantalla 02",
                        direccion = null
                    )
                )
            )
        ),
        Pista(
            nombre = "Pista 05",
            ubicacion = Location("Proveedor").apply{
                latitude = 31.742138
                longitude = -106.432306
            },
            cuerpo = informacion(
                texto = "Este es el texto de la pista 03",
                imagen = null
            ),
        ),
    )
}