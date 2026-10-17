package cl.ucm.models

class Tradeo (
    val itemPago: String,
    val cantidadPago: Int,
    val itemVende: String,
    val cantidadVende: Int 
) {
    init {
        require(itemPago.isNotBlank()) { "Item de pago inválido" }
        require(itemVende.isNotBlank()) { "Item en venta inválido" }
        require(cantidadPago > 0) { "Cantidad de item de pago inválida" }
        require(cantidadVende > 0) { "Cantidad de item en venta inválida" }
    }
}