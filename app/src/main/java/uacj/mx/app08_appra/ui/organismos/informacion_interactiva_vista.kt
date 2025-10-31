package uacj.mx.app08_appra.ui.organismos

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import uacj.mx.app08_appra.modelos.InformacionInteractiva

@Composable
fun InformacionInteractivaVista(informacion_interactiva: InformacionInteractiva){
    Column {
        Text("${informacion_interactiva.texto}")

        for(boton in informacion_interactiva.lista_botones){
            Text("Botón para ir a ${boton.texto}")
        }
    }
}