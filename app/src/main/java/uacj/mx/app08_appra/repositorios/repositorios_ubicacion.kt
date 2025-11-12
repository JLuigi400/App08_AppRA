package uacj.mx.app08_appra.repositorios


import android.location.Location
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object repositorio_ubicacion{
    @Provides
    @Singleton
    fun crea_repositorio_gestor_ubicacion(): RepositorioUbicacion{
        return InstanciaRepositorioUbicacion()
    }
}

class InstanciaRepositorioUbicacion(
    override val ubicacion: MutableState<Location> = mutableStateOf(Location("juego_ra"))
): RepositorioUbicacion{}

interface RepositorioUbicacion{
    val ubicacion: MutableState<Location>
}