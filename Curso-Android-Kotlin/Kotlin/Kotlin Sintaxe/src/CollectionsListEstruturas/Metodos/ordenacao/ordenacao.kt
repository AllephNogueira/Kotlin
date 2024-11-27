package CollectionsListEstruturas.Metodos.ordenacao

/**
 * Ordenando uma lista
 *
 * ASC = do menor para o maior 1 2 3 4 5 6 7 8 9 10
 * DESC = do maior para o menor 10 9 8 7 6 5 4 3 2 1
 *
 * Lembrar que esse metodo ele retorna, então devemos pegar os dados corretos e adicionar em outra variavel.
 */

fun main() {
    val listaDeNumeros = listOf(10,20,8,6,4,7,8,2,5456,4864,894,1,564,89,2,0,1,994,984,4566,1)

    val listaOrdenadaASC = listaDeNumeros.sorted() // ORDENAÇÃO ASC
    println(listaOrdenadaASC)

    val listaOrdenadaDESC = listaDeNumeros.sortedDescending() // DESC = Maior para o menor
    println(listaOrdenadaDESC)

}