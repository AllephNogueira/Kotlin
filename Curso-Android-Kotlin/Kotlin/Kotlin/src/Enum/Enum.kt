package Enum

enum class StatusPedidosEnum {
    AGARDANDO_APROVACAO, //0
    PEDIDO_REALIZADO, // 1
    PAGAMENTO_CONFIRMADO, // 2
    PEDIDO_ENVIADO, // 3
    PEDIDO_ENTREGUE // 4

}

class Pedido (var total: Double = 0.0, var itens: String = "", var statusPedidos: StatusPedidosEnum = StatusPedidosEnum.AGARDANDO_APROVACAO){}


fun main() {

    // Tela de compras
    val pedido = Pedido(125.90, "Camiseta, relogio")

    // Imagina que o usuario esta passando o cartao
    pedido.statusPedidos = StatusPedidosEnum.PEDIDO_REALIZADO
    println("Status pedido: ${StatusPedidosEnum.PEDIDO_REALIZADO.ordinal}")


    // Historicos de compras
    if (pedido.statusPedidos == StatusPedidosEnum.PEDIDO_REALIZADO){
        println("Pedido realizado")
    }else if (pedido.statusPedidos == StatusPedidosEnum.PAGAMENTO_CONFIRMADO){
            println("Pagamento confirmado")
        }else if (pedido.statusPedidos == StatusPedidosEnum.PEDIDO_ENVIADO) {
        println("Seu pedido foi enviado.")
    }

}