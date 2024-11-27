package CollectionsListEstruturas.Map

/**
 * Mutavel = podemos alterar, ou seja adicionar algo depois de esta pronto
 */

data class Produto (
    val nome: String
)
fun main() {

    val produtosCarrinho = mutableMapOf(121345546 to "Relogio", 6564564 to "Pulseira Relogio", 465456456 to "Tapete carro")


    // Imprimindo a lista de produtos no carrinho
    produtosCarrinho.forEach { codigo, produto -> println("Codigo: $codigo - produto: $produto")  }

    // ADICIONANDO mais produtos ao carrinho
    // Essas sao as duas formas de adicionar novos produtos a lista
    produtosCarrinho[45546] = "Capinha de celular"
    produtosCarrinho.put(4564564, "Fone de ouvido")

    // Imprimir o novo map, com os novos produtos

    produtosCarrinho.forEach { codigo, produto -> println("Codigo $codigo - produto: $produto")  }

    // Agora usando um data class, para adicionar produtos.

    println("\n\nPRODUTOS DA DATE CLASS")
    val produtoDateClass = mutableMapOf(5456 to Produto("Notebook"))

    produtoDateClass.forEach { produto -> println(produto)  }


    // Agora vamos imaginar que eu queira pegar apenas um item desse map
    // Passamos a chave para a variavel
    println("\n\nPEGANDO PRODUTO DE FORMA UNITARIA")

    val produtoUnitario = produtosCarrinho.get(121345546)
    println(produtoUnitario)

}