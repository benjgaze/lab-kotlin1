abstract class Aldeano{
    val id: Int
    val nombre: String
    abstract val profesion: String
    abstract fun getDatos()
}

class Granjero(id: Int, nombre: String): Aldeano {
    override val profesion: String = "Granjero"
    
}