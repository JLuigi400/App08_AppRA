package uacj.mx.app08_appra.modelos

public interface PistaGenerica{
    val tipo: TiposDePistas
    val texto: String?
}

data class informacion( // Es la Pista con solo texto y posiblemente una imagen
    override val tipo: TiposDePistas = TiposDePistas.texto,
    override val texto: String,
    val imagen: String? = null
): PistaGenerica

data class Boton(
    val texto: String,
    val direccion: PistaGenerica?
)

data class InformacionInteractiva(
    override val tipo: TiposDePistas = TiposDePistas.interactivo,
    override val texto: String?,
    val lista_botones: List<Boton>
) : PistaGenerica