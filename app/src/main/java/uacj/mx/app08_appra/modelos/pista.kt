package uacj.mx.app08_appra.modelos

import android.location.Location

data class Pista(
    var nombre: String,
    var ubicacion: Location,
    var distancia_maxima: Float,
    var cuerpo: PistaGenerica

)