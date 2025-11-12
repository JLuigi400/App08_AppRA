package uacj.mx.app08_appra.view_models

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uacj.mx.app08_appra.modelos.Pista
import uacj.mx.app08_appra.repositorios.RepositorioInformacionGeneral
import javax.inject.Inject

@HiltViewModel
class ControladorGeneral @Inject constructor(
    val informacion_general: RepositorioInformacionGeneral
) : ViewModel(){
    fun usuario_si_identifico_pista(pista: Pista){
        informacion_general.pistas_identificadas.value
    }
}