package uacj.mx.app08_appra

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.Manifest
import android.os.Bundle
import android.util.Log
import android.util.Pair
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import uacj.mx.app08_appra.modelos.gestor_permisos.SolicitudPermisos
import uacj.mx.app08_appra.ui.pantallas.Principal
import uacj.mx.app08_appra.ui.theme.App08_AppRATheme

class MainActivity : ComponentActivity() {
    private lateinit var conexion_para_obtener_ubicacion: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App08_AppRATheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var texto_ubicacion by remember { mutableStateOf("No tengo permisos para ver tu ubicación qnq") }
                    var mostrar_resultado_permisos by remember { mutableStateOf(false) }
                    var texto_permisos_obtenidos by remember { mutableStateOf("Todos los permisos obtenidos") }

                    SolicitudPermisos(
                        con_permisos_obtenidos = {
                            mostrar_resultado_permisos = true
                            obtener_ubicacion_usuario(
                                ubicacion_actualizada = { ubicacion ->
                                    Log.v("UBICACIÓN","${ubicacion.first}")
                                    Log.v("UBICACIÓN","${ubicacion.second}")
                                    texto_ubicacion = "Latitud: ${ubicacion.first} Longitud: ${ubicacion.second}"
                                },
                                fallo_obtener_ubicacion = { error_encontrado ->
                                    texto_ubicacion = "Error ${error_encontrado.localizedMessage}"
                                },
                                ubicacion_actualizada_no_disponible = {
                                    texto_ubicacion = "Error: La ubicación no está disponible por ningún motivo"
                                }
                            )
                        },
                        sin_permisos_obtenidos = {
                            mostrar_resultado_permisos = true
                            texto_permisos_obtenidos = "No tengo todos los permisos para funcionar qnq"
                        },
                    ) { }

                    Text(texto_ubicacion)
                }
            }
        }
    }

    @SuppressLint("MissingPermision")
    fun obtener_ubicacion_usuario (
        ubicacion_actualizada: (Pair<Double, Double>) -> Unit,
        fallo_obtener_ubicacion: (Exception) -> Unit,
        ubicacion_actualizada_no_disponible: () -> Unit
    ){
        conexion_para_obtener_ubicacion = LocationServices.getFusedLocationProviderClient(this)

        if(tenemos_permisos_ubicacion()){
            conexion_para_obtener_ubicacion.lastLocation
                .addOnSuccessListener {
                    ubicacion ->
                    if(ubicacion != null){
                        ubicacion_actualizada(Pair(ubicacion.latitude, ubicacion.longitude))
                    }else{
                        ubicacion_actualizada_no_disponible()
                    }
                }.addOnFailureListener{ error ->
                    fallo_obtener_ubicacion(error)
                }
        }
    }

    @SuppressLint("MissingPermission")
    fun obtener_ubicacion(
        obtener_ubicacion: (Pair<Double, Double>) -> Unit,
        obtener_error: (Exception) -> Unit,
        prioridad: Boolean = true
    ){
        val precision = if(prioridad) Priority.PRIORITY_HIGH_ACCURACY else Priority.PRIORITY_BALANCED_POWER_ACCURACY

        if(tenemos_permisos_ubicacion()){
            conexion_para_obtener_ubicacion.getCurrentLocation(
                precision, CancellationTokenSource().token
            ).addOnSuccessListener {
                ubicacion -> if(ubicacion != null){
                    obtener_ubicacion(Pair(ubicacion.latitude, ubicacion.longitude))
                }
            }
                .addOnFailureListener { error -> obtener_error(error) }
        }
    }

    private fun tenemos_permisos_ubicacion(): Boolean{
        return(ActivityCompat.checkSelfPermission(
            this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    App08_AppRATheme {
        Principal()
    }
}