package uacj.mx.app08_appra

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.Manifest
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.util.Pair
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
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
import androidx.hilt.lifecycle.viewmodel.HiltViewModelFactory
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import dagger.hilt.android.AndroidEntryPoint
import uacj.mx.app08_appra.modelos.gestor_permisos.SolicitudPermisos
import uacj.mx.app08_appra.ui.controladores.NavegacionPrincipal
import uacj.mx.app08_appra.ui.pantallas.Principal
import uacj.mx.app08_appra.ui.theme.App08_AppRATheme
import uacj.mx.app08_appra.view_models.GestorUbicacion
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var conexion_para_obtener_ubicacion: FusedLocationProviderClient
    private lateinit var puente_recibir_update_ubicacion: LocationCallback

    private var ubicacion_actual = Location("juego_ra")

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App08_AppRATheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var texto_ubicacion by remember { mutableStateOf("No tengo permisos para ver tu ubicación qnq") }
                    var mostrar_resultado_permisos by remember { mutableStateOf(false) }
                    var texto_permisos_obtenidos by remember { mutableStateOf("Todos los permisos obtenidos") }

                    var gestor_ubicacion: GestorUbicacion = hiltViewModel()

                    SolicitudPermisos(
                        con_permisos_obtenidos = {
                            mostrar_resultado_permisos = true

                            obtener_ubicacion_usuario(
                                ubicacion_actualizada_correctamente = { ubicacion_actual ->
                                    Log.w(
                                        "Ubicacion Nueva",
                                        "La ubicacion nueva es: ${ubicacion_actual}"
                                    )
                                    gestor_ubicacion.actualizar_ubicacion_actual(ubicacion_actual)
                                },
                                /*
                                fallo_obtener_ubicacion = { error_encontrado ->
                                    texto_ubicacion = "Error ${error_encontrado.localizedMessage}"
                                },
                                ubicacion_actualizada_no_disponible = {
                                    texto_ubicacion = "Error: La ubicación no está disponible por ningún motivo"
                                }
                                */
                            )
                        },
                        sin_permisos_obtenidos = {
                            mostrar_resultado_permisos = true
                            texto_permisos_obtenidos = "Error: La ubicación es nula por algun motivo u.u' "
                        }
                    ) { }

                    
                    NavegacionPrincipal(modificador = Modifier.padding(innerPadding))
                }
            }
        }
    }

    fun actualizar_ubicacion(ubicacion: Location){
        Log.wtf("UBICACIÓN","Ubicacion actualizada ${ubicacion}")
        ubicacion_actual = ubicacion
    }

    @SuppressLint("MissingPermission")
    fun obtener_ubicacion_usuario (
        ubicacion_actualizada_correctamente: (Location) -> Unit,
        //ubicacion_actualizada: (Pair<Double, Double>) -> Unit,
        //fallo_obtener_ubicacion: (Exception) -> Unit,
        //ubicacion_actualizada_no_disponible: () -> Unit
    ){
        conexion_para_obtener_ubicacion = LocationServices.getFusedLocationProviderClient(this)

        puente_recibir_update_ubicacion = object: LocationCallback(){
            override fun onLocationResult(ubicaciones: LocationResult) {
                for(ubicacion in ubicaciones.locations){
                    ubicacion_actualizada_correctamente(ubicacion)
                }
            }
        }

        if(tenemos_permisos_ubicacion()){

            val constructor_puentes_ubicacion = LocationRequest
                .Builder(TimeUnit.SECONDS.toMillis(2))
                .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
                .build()

            conexion_para_obtener_ubicacion.requestLocationUpdates(
                constructor_puentes_ubicacion,
                puente_recibir_update_ubicacion,
                Looper.getMainLooper()
            )
        }
    }

    @SuppressLint("MissingPermission")
    fun obtener_ubicacion(
        obtener_ubicacion: (Pair<Double, Double>) -> Unit,
        obtener_error: (Exception) -> Unit,
        prioridad: Boolean = true
    ){
        val precision = if(prioridad) Priority.PRIORITY_HIGH_ACCURACY else
            Priority.PRIORITY_BALANCED_POWER_ACCURACY

        if(tenemos_permisos_ubicacion()){
            conexion_para_obtener_ubicacion.getCurrentLocation(
                precision, CancellationTokenSource().token
            ).addOnSuccessListener { ubicacion ->
                if(ubicacion != null){
                    obtener_ubicacion(Pair(ubicacion.latitude, ubicacion.longitude))
                }
            }
                .addOnFailureListener{ error ->
                    obtener_error(error)
                }
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

    }
}