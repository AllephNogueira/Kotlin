package Collections

import kotlin.math.min

data class Cliente(
    val nome: String,
    val idade: Int
)

fun main() {

    val cliente1 = Cliente("Alleph", 30)
    val cliente2 = Cliente("Fernanda", 28)
    val cliente3 = Cliente("Crixus", 8)

    /**
     * Vamos criar um Array
     * Agora imagina que temos o usuario que quer adicionar mais produtos, ele não vai conseguir.
     * Porque nao temos o metodo .add
     * Embora temos o plus, mas teriamos que ficar criando listas a todo momento que não é indicado
     * Então veja a diferença com o List.
     *
     * Podemos mudar(mutavel) o valores dos itens, onde esta arroz vamos colocar ovo, mas nao podemos adicionar novos itens.
     */
    println("Array")
    val listaArray = arrayOf("Arroz", "Feijao", "Carne")
    listaArray.forEach { produtos -> println(produtos) }


    /**
     * Agora vamos criar uma list
     * Na lista nao podemos alterar o valor, ou seja, não vamos mudar banana para ovo.
     */

    println("\nLista")
    val minhaLista = listOf<String>("Banana", "Maça", "Feijao")

    minhaLista.forEach { produtos -> println(produtos) }

    println("\nMutableList")
    /**
     * Aqui podemos adicionar novos valores.
     * Como o nome ja diz ela é mutavel.
     * Repara que podemos adicionar um produto e ele vai para o final da fila.
     */

    val listaMutavel = mutableListOf<String>("Arroz", "Pera", "Frango")

    listaMutavel.add("Macarrao") // Adicionar um item a lista
    listaMutavel.addAll(minhaLista) // Adicionar todos os items da outra lista.
    listaMutavel.remove("Frango") // Remover frango da lista.
    listaMutavel.forEach{ p -> println(p)}

    println("\nMutableList - Clientes")
    val mutableListClientes = mutableListOf(
        cliente1, cliente2, cliente3
    )
    mutableListClientes.forEach { cliente -> println("${cliente.nome} - ${cliente.idade}") }



    val listaMutableEmbaralhada = mutableListClientes.shuffled() // embaralhar a lista * Atençao, ele vai retornar sempre uma nova lista.
    listaMutableEmbaralhada.forEach { cliente -> println("${cliente.nome} - ${cliente.idade}") }



    mutableListClientes.clear() // Limpar a lista.
}