package Loops

/**
 * Atençao o IN, pode ser lido como ate
 * Contagem começa com 1 ATE 10
 * I começa com 5 ate 1
 */
fun main() {
    for (contagem in 1..10) {
        println(contagem)
    }

    for (i in 1..10 step 2) {
        // Especifica um passo para o intervalo.
        println(i)
        // Output: 1 3 5 7 9
    }

    for (i in 5 downTo 1) {
        //Itera sobre um intervalo de números em ordem decrescente.
        println(i)
        // Output: 5 4 3 2 1
    }


    // Itera sobre os índices de uma lista.
    val fruits = listOf("Apple", "Banana", "Cherry")
    for (i in fruits.indices) {
        println("Fruit at index $i is ${fruits[i]}")
        // Output:
    // Fruit at index 0 is Apple
    // Fruit at index 1 is Banana
    // Fruit at index 2 is Cherry
    }


    //Itera diretamente sobre os elementos de uma coleção.
    val fruits2 = listOf("Apple", "Banana", "Cherry")
    for (fruit in fruits2) {
        println(fruit)
    // Output: Apple Banana Cherry
    }




}