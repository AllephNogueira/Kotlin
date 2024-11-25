package CollectionsList.Metodos.Map

/**
 * Com o map, podemos fazer ordenações, alterações ou filtrar elementos da lista.
 *
 * Reparar que esse metodo MAP é diferente do map que vimos(CHAVE, "VALOR")
 *
 * Esse metodo map é o mapeamento e transformação.
 *
 * Metodos com o map
 *
 * Map
 * Filter
 * Union
 * + e -
 * Sorted, sortedDescending
 *
 *
 * Operações com o metodo map, cria uma lista com o mesmo numero de itens ou os itens transformado
 * Imagina que temos um app de frutas com as frutas
 * Banana - Maçã - Pera
 * E ai podemos transformar esses dados em outro
 * Uva - Morango - Abacaxi
 */

fun main() {
    val listaDeNomes = listOf("Alleph", "Fernanda", "Crixus", "Amora")

    // Imagina que temos esse nome, mas queremos transformar algo em todos os nomes.

    val novaLista = mutableListOf<String>() // Como estamos criando uma lista vazia, o Kotlin não sabe o tipo de dado que vai entrar, então precisamos dizer o tipo da lista

    listaDeNomes.forEach { nome ->
        novaLista.add(nome.uppercase()) // Estamos adicionando os nomes da listaNome para dentro novaLista, e colocando tudo em maiusculo
        // Observa que dentro desse forEach fizemos uma TRANSFORMAÇÃO
    }
    println(novaLista)

    // Agora vamos fazer de uma maneira mais simples utilizando o MAP
    // O map é como se fosse um forEach, ele vai percorrer cada elemento da lista e dentro do bloco vamos dizer qual vai ser a transformação.
    val listaComMap = listaDeNomes.map{ nome ->
        nome.lowercase() // Transformando tudo em minusculo
    }

    println(listaComMap)


    // Maneira mais curta de fazer
    val listaComMapCurta = listaDeNomes.map { it.lowercase()  }
    println(listaComMapCurta)








    // Convertendo tudo para letra maiuscula
//    listaDeNomes.forEach { nome ->
//        println(nome.uppercase())
//    }










    // outra forma de fazer utilizando o for

//    for (nome in listaDeNomes) {
//        println(nome)
//    }
}


