package ArraysELoops

fun main() {
    // Lembrar que com val, voce pode alterar os dados dentro do array, ou seja, mudar o nome das pessoas
    // Mas nao pode recriar o array, ou seja, refazer um novo array nomes.
    val nomes = arrayOf("Alleph", "Fernanda", "Crixus", "Amora")

    println(nomes[1])

    // mudando valor do array
    nomes[1] = "Anastacia"

    println(nomes[1])
}