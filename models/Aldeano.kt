package cl.ucm.models

abstract class Aldeano(
    val nombre: String?,
    val posicion: String
) {
    init {
        require(posicion.isNotBlank) println("Posición no ingresada.")
    }
    private val id: Int = 0 // Luego hacer una lista con IDs para asignar la sgte. libre
    abstract val profesion: String
    private val tradeos: MutableList<Tradeo> = mutableListOf()

    fun verIntercambios(): List<Tradeo> = tradeos.toList()

    fun agregarTradeo(
        itemPago: String,
        cantidadPago: Int,
        itemVende: String,
        cantidadVende: Int,
    ): Boolean {
        return try {
            tradeos.add(Tradeo(itemPago, cantidadPago, itemVende, cantidadVende))
            true
        } catch (error: IllegalArgumentException) {
            println("No se pudo agregar el intercambio: ${error.message}")
            false
        }
    }

}

class Granjero(
    nombre: String? = null,
    posicion: String
) : Aldeano(posicion, nombre) {
    override val profesion: String = "Granjero"
}