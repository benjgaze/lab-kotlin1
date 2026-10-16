abstract class Aldeano(
    protected val nombre: String,
    protected val posicion: String, // Considerar cambio a var
    protected val nivel: Int // Considerar cambio a var
) {
    init {
        // USAR REQUIRE
    }
    constructor(nombre: String, posicion: String, nivel: Int) : this (
        nombre = "", // Crear generador de nombres aleatorios
        posicion = "Unknown",
        nivel = 1
    )
    protected abstract val id: Int
    protected abstract val profesion: String

    fun verDatos() {
        println("=====================================")
        println("Id: $id")
        println("Nombre: $nombre")
        println("Coordenadas (x,y,z): $coordenadas")
        println("Nivel: $nivel")
        println("Profesion: $profesion")
        println("=====================================")
    }
    
    abstract fun verIntercambios()
    
}

class Granjero(
    nombre: String,
    coordenadas: String,
    nivel: Int
) : Aldeano(nombre, posicion, nivel) {
    override val id: Int = "0" // Crear listas de ids del sistema y asignar siempre la primera siguiente libre
    override val profesion: String = "Granjero"
}