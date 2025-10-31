package uacj.mx.app08_appra.modelos

import android.location.Location

data class Pista(
    var nombre: String,
    var ubicacion: Location,
    var cuerpo: PistaGenerica

)