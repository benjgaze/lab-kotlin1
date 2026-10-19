# Minecraft Villager Management

## Descripción del problema

En los mundos multijugador técnicos o de supervivencia a largo plazo en Minecraft, es común agrupar
decenas de aldeanos en zonas conocidas como "Trading Halls" (salas de comercio).
Sin embargo, a medida que la zona crece, la cantidad de aldeanos se vuelve inmanejable.

Hoy en día, los jugadores intentan organizar esta zona usando carteles de madera o
simplemente confiando en su memoria. Esto genera varios problemas:

1. Pérdida de tiempo: Los jugadores olvidan qué aldeano vende qué objeto exacto y pasan
    minutos buscando sin rumbo.
2. Posición: No hay registro claro de en qué coordenadas exactas se
    encuentra el aldeano que vende un encantamiento crucial (como Mending/Reparación).
3. Falta de administración: Cualquier jugador nuevo puede entrar, alterar los intercambios
    o perder aldeanos sin que haya un registro de quién hizo qué.

**Minecraft Villager Management** reemplaza los carteles y la memoria por un sistema de
registro estructurado. El sistema permite buscar rápidamente qué aldeano vende el objeto
deseado, conocer sus coordenadas exactas y restringir quién puede añadir o eliminar aldeanos
de la base de datos.

Alcance de esta versión:

- Sistema interactivo por consola.
- Datos almacenados temporalmente en memoria durante la ejecución.
- Fuera de alcance: Modificaciones directas dentro del juego (no es un mod, es una
  herrramienta externa de gestión).

## Usuarios del sistema

El sistema divide a los jugadores en tres categorias según su nivel de confianza y experiencia en
el servidor:

| Usuario | Tarea desempeñada en el sistema |
| :---: | :---: |
| Noob | Tiene permisos de solo lectura. Su vista es superficial: busca objetos<br>a la venta y consulta las coordenadas para saber a dónde ir.|
| Pro | Tiene permisos de escritura. Además de consultar, es el encargado<br>de registrar nuevos aldeanos en el sistema y eliminarlos si mueren o<br>son movidos. |
| Admin | Tiene el control total. Se encarga de mantener el orden, con la<br>capacidad exclusiva de expulsar (banear) a usuarios problemáticos<br>del sistema. |

## Diagrama UML (Avance)

A continuación, se detalla la estructura lógica del sistema:

```mermaid
classDiagram
    direction LR
    
    class Servidor {
        <<Singleton>>
        - instancia: Servidor
        - aldeanosRegistrados: List~Aldeano~
        - usuariosConectados: List~Usuario~
        + getInstance(): Servidor
        + iniciarSesion(nickname: String): Usuario
        + buscarTradeo(objeto: String): List~Aldeano~
        + registrarAldeano(pro: Pro, aldeano: Aldeano): boolean
        + removerAldeano(pro: Pro, idAldeano: int): boolean
        + banearUsuario(admin: Admin, usuarioABanear: Usuario): boolean
    }

    class AldeanoFactory {
        <<Factory>>
        + crearAldeano(profesion: String, nombre: String, posicion: String): Aldeano
    }

    class Usuario {
        <<abstract>>
        # nickname: String
        # consultarTradeos()
        # verMapaAldeanos()
    }

    class Noob {
        + consultarNivel()
    }
    
    class Pro {
        - registrarNuevoAldeano(nombre: String): Bool
        - removerAldeano(id: int): Bool
    }
    
    class Admin {
        - expulsarUsuario(nickname: String)
        - mostrarDatos(): String
    }

    class Aldeano {
        <<abstract>>
        - nombre: String?
        - posicion: String
        - profesion: String
        - id: Int
        - isZombificado: Boolean
        - multiplicadorDescuento: Double
        - tradeos: MutableList~Tradeo~
        + obtenerTradeo(): List~Tradeo~
        + agregarTradeo(itemPago: String, cantidadPago: Int, itemVende: String, cantidadVende: Int): Boolean
        + curarZombi()
    }

    class Tradeo {
        - itemPago: String
        - cantidadPago: Int
        - itemVende: String
        - cantidadVende: Int 
        + aplicarDescuento(multiplicador: Double): Int
    }

    class Granjero {
        - tipoSemillaPrincipal: String
        + recolectarCultivos()
    }
    
    class Bibliotecario {
        - tieneLibrosMending: Boolean
        + desencantarLibro()
    }
    
    class HerreroArmas {
        - materialEspecialidad: String
    }
    
    class HerreroHerramientas {
        - reparaHerramientas: Boolean
    }
    
    class Flechero {
        - fabricaFlechasEfecto: Boolean
    }
    
    class Clerigo {
        - nivelMagia: Int
    }
    
    class Cartografo {
        - mapasExplorados: Int
    }

    %% HERENCIA
    Aldeano <|-- Granjero
    Aldeano <|-- Bibliotecario
    Aldeano <|-- HerreroArmas
    Aldeano <|-- HerreroHerramientas
    Aldeano <|-- Flechero
    Aldeano <|-- Clerigo
    Aldeano <|-- Cartografo

    Usuario <|-- Noob
    Usuario <|-- Pro
    Usuario <|-- Admin

    %% RELACIONES
    Aldeano "1" *-- "0..*" Tradeo
    Servidor "1" *-- "0..*" Aldeano : Controla
    Servidor "1" o-- "0..*" Usuario : Administra
    Usuario ..> Servidor : Interactua con
    AldeanoFactory ..> Aldeano : Instancia
```

![image](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSN60cRLfe2wYPUMDscFPGhzHs6EzydyBI1b-2ZswZi1nxPdUkII5QLp0g&s=10)
