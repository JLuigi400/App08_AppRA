package uacj.mx.app08_appra.repositorios

import android.location.Location
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uacj.mx.app08_appra.modelos.Pista
import java.util.Collections.emptyList
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object repositorio_informacion_general_singleton{
    @Provides
    @Singleton
    fun crea_repositorio_informacion_general(): InstanciaRepositorioInformacionGeneral {
        return InstanciaRepositorioInformacionGeneral()
    }
}

class InstanciaRepositorioInformacionGeneral(
    override val pistas_identificadas: MutableState<List<Pista>> = mutableStateOf(emptyList())
): RepositorioInformacionGeneral{}

interface RepositorioInformacionGeneral{
    val pistas_identificadas: MutableState<List<Pista>>
}