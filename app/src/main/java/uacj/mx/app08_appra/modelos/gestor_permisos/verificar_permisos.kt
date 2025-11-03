package uacj.mx.app08_appra.modelos.gestor_permisos

import android.Manifest
import androidx.collection.emptyObjectList
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.input.key.Key
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.common.util.CollectionUtils.listOf
import java.util.Collections.emptyList

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SolicitudPermisos(
    con_permisos_obtenidos:() -> Unit,
    sin_permisos_obtenidos:() -> Unit,
    con_permisos_revocados:() -> Unit,
){
    val estado_permisos = rememberMultiplePermissionsState(
        listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    ){
        lista_permisos ->
        var tengo_todos_permisos: Boolean = false // Variable bandera o Flag

        for (permiso in lista_permisos.values){
            if(!permiso){
                tengo_todos_permisos = false
                break
            }else{
                tengo_todos_permisos = true

            }
        }

        if(tengo_todos_permisos){
            con_permisos_obtenidos.invoke()
        }else{
            sin_permisos_obtenidos.invoke()
        }
    }

    LaunchedEffect(key1 = estado_permisos) {
        val tengo_permisos_revocados = estado_permisos.revokedPermissions.size == estado_permisos.permissions.size

        var lista_permisos_por_pedir: List<PermissionState> = emptyList<PermissionState>()

        for(permiso in estado_permisos.permissions){
            if(!permiso.status.isGranted){
                lista_permisos_por_pedir.add(permiso)
            }
        }

        if(!lista_permisos_por_pedir.isEmpty()){
            estado_permisos.launchMultiplePermissionRequest()
        }

        if(tengo_permisos_revocados){
            con_permisos_revocados()
        }else{
            if(estado_permisos.allPermissionsGranted){
                con_permisos_obtenidos()
            }else{
                sin_permisos_obtenidos()
            }
        }
    }
}