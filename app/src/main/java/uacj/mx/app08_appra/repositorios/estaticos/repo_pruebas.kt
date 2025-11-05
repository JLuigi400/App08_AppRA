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
            latitude = 31.7156044
            longitude = -106.4246012
        },
        distancia_maxima = 15.0f,
        cuerpo = informacion(
            texto = "Prueba de texto para comprobar pista 1",
            imagen = null
        )
    ),
        Pista(
            nombre = "Pista 02",
            ubicacion = Location("Proveedor").apply{
                latitude = 31.7156044
                longitude = -106.4246012
            },
            distancia_maxima = 15.0f,
            cuerpo = informacion(
                texto = "Este es el texto de la pista 02",
                imagen = null
            ),
        ),
        Pista(
            nombre = "Pista 03",
            ubicacion = Location("Proveedor").apply{
                latitude = 31.7156044
                longitude = -106.4246012
            },
            distancia_maxima = 50.0f,
            cuerpo = informacion(
                texto = "Este es el texto de la pista 03",
                imagen = null
            ),
        ),
        Pista(
            nombre = "Pista 04",
            ubicacion = Location("Proveedor"),
            distancia_maxima = 75.0f,
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
        )
    )
}