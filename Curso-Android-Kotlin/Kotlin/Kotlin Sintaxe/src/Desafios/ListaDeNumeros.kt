package Desafios

/** Temos uma lista de um numero que pode ter numeros repetidos, precisamos organizar essa lista e retirar o unico numero que nao se repete */

fun main() {
    // Array de numeros
    val arr = arrayOf(1,2,3,4,10,2,1,7,7,10,12,12,9,27,27)

    // Precisamos organizar esse array
    val arrayOrganizado = arr.sorted()

    // Agora precisamos saber a quantidade de itens que tem dentro dessa lista

    println("Total de indices: ${arrayOrganizado.size}")
    println("Lista organizada: $arrayOrganizado")


    for (i in 0 until arrayOrganizado.size -1) {
        val temporario = arrayOrganizado[i]
        val proximoNumero = arrayOrganizado[i+1]


        println("NUMERO: $temporario ------------- INDICE : $i")
        println("NUMERO: $proximoNumero ------------- SEGUNDO INDICE : ${i+1}\n")


        if (temporario != proximoNumero){
            println("Encontramos um numero diferente $proximoNumero")

        }
    }


}