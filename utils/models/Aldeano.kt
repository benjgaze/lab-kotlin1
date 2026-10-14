abstract class Aldeano{
    val id: Int
    val nombre: String
    val coordenadasXYZ: String
    val nivel: Int
    abstract val profesion: String
    fun getDatos() {
        //print a todos los datos, solo para PRO y ADMIN
    }
    abstract fun getIntercambios()
    abstract fun getCoordenadas()
}

class Granjero(id: Int, nombre: String): Aldeano {
    // Constructor secundario que lea lista de id's para asignar uno libre y generar nombre aleatorio en caso de no introducir
    override val profesion: String = "Granjero"
    
}