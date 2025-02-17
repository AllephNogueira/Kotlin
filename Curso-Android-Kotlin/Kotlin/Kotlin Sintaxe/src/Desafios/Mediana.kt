package Desafios

/* Vamos receber um array e queremos saber a mediana dele
*
* Se for impar, pegamos o numero do meio
* Ex: 1 2 3 4 5 = meio 3
*
* Se for par pegamos os 2 numeros do meio
* Ex 1 2 3 4 5 6 = 3 + 4
* = 7 / 2 = 3.5
*
* */

fun main() {
    val arr = arrayOf(10,5,2,4,4,54,554,5)


    val arraOrdenado = arr.sorted() // Ordenando o array

    println(arraOrdenado) // Imprimindo a arr ordenado


    // Olhando se é par ou impar
    if(arr.size % 2 == 0) { // Pegando o tamanho do array para verificar se é par ou impar
        // Vamos pegar os 2 itens do meio, vamos somar e depois dividir por 2
        // Numero1: Pega o item do array divide por 2 e desce 1 casa
        // Imagina um array com 10 elementos, divide por 2 ficamos na casa 5 -1 descemos uma casa e vamos para a casa = 4
        val numero1 = arraOrdenado[arr.size / 2 - 1].toDouble()
        val numero2 = arraOrdenado[arr.size / 2].toDouble()
        val somaMediana = ((numero1 + numero2) / 2).toInt()
        println(somaMediana)


    }else {
        // Se for impar, vamos pegar apenas o numero do meio
        val numeroDoMeio = arr.size / 2
        println("Impar: ${arraOrdenado[numeroDoMeio]} ")
    }

}

