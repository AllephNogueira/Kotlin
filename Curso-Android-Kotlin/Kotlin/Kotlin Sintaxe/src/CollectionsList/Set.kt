package CollectionsList

/**
 * Set são coleções de itens unicos, ou seja os itens não se repetem.
 *
 * Set funciona igual a lista, temos o imutavel
 *
 * Mas podemos fazer o mesmo esquema da lista para ter o mutavel.
 *
 * Pra que usuariamos o set? Imagina que queremos criar uma aplicação que todos vao adicionando dados, mas esses dados nunca se repetem.
 *
 * Lembrar que no set, temos todos os metodos iguais, retirando apenas o get e o ´[].
 */

data class Carro (
    val nome: String,
    val marca: String
)

fun main() {

    // LISTA IMUTAVEL
    val listaSet = setOf("Alleph", "Nogueira", "Alleph", "Oliveira", "Crixus", "Amora")

    listaSet.forEach { nome -> println(nome)  } // Repara que o nome Alleph nao vai repetir.

    println(listaSet.indexOf("Alleph")) // descobrindo qual a posicação do elemento.




    // LISTA MUTAVEL

    println("Agora na parte de baixo vamos criar uma lista mutavel. (Mudavel)")

    val listaMutavel = mutableListOf<String>("Alleph", "Nogueira", "De", "Oliveira")
    listaMutavel.add("Mariana")
    listaMutavel.remove("De")
    listaMutavel.addAll(listaSet)

    listaMutavel.forEach { nome -> println(nome) }


    // LISTA DE ATRIBUTOS DA CLASSE DATA CLASS

    println("\nCriando agora minha lista de atributos")

    val carro1 = Carro("C4", "Citroen")
    val carro2 = Carro("New Fiesta", "Ford")

    val listaAtributosDataClass = setOf(
        carro1, carro2
    )

    listaAtributosDataClass.forEach { carro -> println("${carro.nome} - ${carro.marca}")  }
}

