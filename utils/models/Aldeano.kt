abstract class Aldeano(
    protected val id: Int,
    protected val nombre: String,
    protected val coordenadas: String,
    protected val nivel: Int
) {
    abstract val profesion: String

    fun getDatos() {
        println("=====================================")
        println("Id: $id")
        println("Nombre: $nombre")
        println("Coordenadas (x,y,z): $coordenadas")
        println("Nivel: $nivel")
        println("Profesion: $profesion")
        println("=====================================")
    }
    abstract fun getIntercambios()
    abstract fun getCoordenadas()
}

class Granjero(
    id: Int,
    nombre: String,
    coordenadasXYZ: String,
    nivel: Int
) : Aldeano(id, nombre, coordenadasXYZ, nivel) {
    // Constructor secundario que lea lista de id's para asignar uno libre y generar nombre aleatorio en caso de no introducir
    override val profesion: String = "Granjero"
}