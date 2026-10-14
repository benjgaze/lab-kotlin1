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
        - whitelistUsuarios: List~Usuario~
        + hacerLogin(nombreUsuario: String): Usuario
        + buscarTradeo(objeto: String): List~Aldeano~
        + registrarAldeano(usuario: Usuario, aldeano: Aldeano): boolean
        + matarAldeano(usuario: Usuario, idAldeano: int): boolean
        + banearUsuario(admin: Admin, usuarioABanear: Usuario): boolean
    }

    class Aldeano {
        <<abstract>>
        # idUnico: int
        # coordenadasXYZ: String
        # profesion: String
        # nivelExperiencia: int
        + getIntercambios()
        + getCoordenadas()
    }

    class Usuario {
        <<abstract>>
        # uuid: String
        # nickname: String
        + verMapaAldeanos()
    }

    class Noob {
        + consultarTradeosSimples()
    }
    
    class Pro {
        + verInventarioCompleto()
        + registrarNuevoAldeano()
    }
    
    class Admin {
        + expulsarUsuario()
        + reiniciarZona()
    }

    class Granjero {
        + getCultivos()
    }
    class Herrero {
        + getArmaduras()
    }
    class Bibliotecario {
        + getLibrosEncantados()
    }

    Aldeano <|-- Granjero
    Aldeano <|-- Herrero
    Aldeano <|-- Bibliotecario

    Usuario <|-- Noob
    Usuario <|-- Pro
    Usuario <|-- Admin

    Servidor *-- Aldeano : Controla
    Servidor *-- Usuario : Administra
    Usuario ..> Servidor : Envía comandos a
```
