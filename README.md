# Minecraft Villager Management 

## Problema a resolver:
Se tiene una cantidad no manejable de aldeanos en cierta zona de un mundo de Minecraft. Los jugadores tienen dificultades para organizar, registrar y administrar
esta zona, por lo tanto, se busca facilitar y llevar a cabo un sistema que permita la gestion eficiente de este recurso digital.

## Usuarios del sistema
| Usuarios | Tarea desempeñada  |
| -------- | -------------------|
| Noob | Vista superficial del registro de aldeanos por objeto a la venta y sus coordenadas |
| Pro | Vista detallada del registro y permisos para registrar aldeanos en el sistema sistema |
| Admin | Tiene el control total del sistema, encargado principalmente de mantener la estabilidad en el sistema |

![image](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSN60cRLfe2wYPUMDscFPGhzHs6EzydyBI1b-2ZswZi1nxPdUkII5QLp0g&s=10)

## Diagrama UML (Avance)

```mermaid
classDiagram
    class Servidor {
        - aldeanosRegistrados: List~Aldeano~
        - usuariosConectados: List~Usuario~
        + iniciarSesion(nickname: String): Usuario
        + buscarTradeo(objeto: String): List~Aldeano~
        + registrarAldeano(pro: Pro, aldeano: Aldeano): boolean
        + removerAldeano(pro: Pro, idAldeano: int): boolean
        + banearUsuario(admin: Admin, usuarioABanear: Usuario): boolean
    }

    class Aldeano {
        <<abstract>>
        # id: int*
        # profesion: String*
        # nombre: String
        # posicion: String 
        # nivel: int
        + verIntercambios()
        + verDatos()
    }

    class Usuario {
        <<abstract>>
        # nickname: String
        # consultarTradeos()
        # verMapaAldeanos()
    }

    class Noob {
    }
    
    class Pro {
        - registrarNuevoAldeano(nombre: String): Bool
        - removerAldeano(id: int): Bool
    }
    
    class Admin {
        - expulsarUsuario(nickname: String)
        - mostrarDatos(): String
    }

    %% LAS PROFESIONES DE MINECRAFT ASIGNADAS
    class Granjero
    class Bibliotecario
    class HerreroArmas
    class HerreroHerramientas
    class Flechero
    class Clerigo
    class Cartografo

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

    Servidor "1" *-- "0..*" Aldeano : Controla

    Servidor "1" o-- "0..*" Usuario : Administra

    Usuario ..> Servidor : Interactua con
```
