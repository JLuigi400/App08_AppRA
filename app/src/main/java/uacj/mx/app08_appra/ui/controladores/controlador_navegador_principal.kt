package uacj.mx.app08_appra.ui.controladores

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import uacj.mx.app08_appra.ui.pantallas.Principal

@Composable
fun NavegacionPrincipal(modificador: Modifier = Modifier){
    var controlador_navegacion = rememberNavController()

    NavHost(controlador_navegacion, startDestination = OpcionNavegacionPantallaPrincipal){
        composable<OpcionNavegacionPantallaPrincipal> {
            Principal(modificador)
        }
    }
}