package Collections.Metodos.Filter

/**
 * Imagina que criamos uma lista e queremos filtrar essa lista para exibir apenas algumas informações
 *
 * Observa que quando voce abre o metodo, voce coloca a regra de negocio
 *
 * É como se fosse um IF, se retornar verdadeiro ele coloca dentro da nova lista.
 *
 */

fun main() {

    val listaFrutas = listOf("maçã", "laranja", "banana", "uva") // Minha lista
    val listaVendas = listOf(100,150,1800,160,20,30,44,58,90) // Lista de vendas

    val novaLista = listaFrutas.filter{    // Criando uma nova lista com os itens filtrados.
        fruta -> fruta == "uva"
    }

    println(novaLista)


    // Nesse exemplo estamos filtrando apenas as vendas maiores que 80 reais.
    val filtrarVendas = listaVendas.filter { valorVenda ->
        valorVenda > 80 //condição.
    }

    filtrarVendas.forEach{ venda -> println(venda)}

    // Outra maneira de fazer mais curta
    // IT é a palavra chave, podemos alterar ela por qualquer outra, poderiamos usar venda.
    // Mas assim fica mais curto.
    val vendasMenorQueCem = listaVendas.filter {it < 100}
    vendasMenorQueCem.forEach {
        println(it)
    }
}