package testesDeCodigo

/*
Entendi! Vamos simplificar o problema:

Objetivo: Dado um array de inteiros, calcule as proporções de elementos que são positivos, negativos e zeros. Imprima cada proporção em uma nova linha com seis casas decimais.

Exemplo: Para um array de 6 elementos: [-4, 3, -9, 0, 4, 1]

Positivos: 3, 4, 1 (3 elementos)

Negativos: -4, -9 (2 elementos)

Zeros: 0 (1 elemento)

Proporções:

Positivos:
3
6
=
0.500000

Negativos:
2
6
=
0.333333

Zeros:
1
6
=
0.166667

Passos a seguir:

Conte quantos números são positivos, negativos e zeros no array.

Calcule a proporção de cada tipo de número.

Imprima as proporções com seis casas decimais.

Código Kotlin: Vamos completar a função plusMinus:
 */

/*
 * Complete the 'plusMinus' function below.
 *
 * The function accepts INTEGER_ARRAY arr as parameter.
 */


fun plusMinus(arr: Array<Int>): Unit {

    // Tamanho do array
    val n = arr.size

    // Variaveis para armazenar a quantidade de numeros (Positivos, Negativos e Zero)
    var numerosPositivos = 0
    var numerosNegativos = 0
    var quantidadeDeZeros = 0


    // Loop para acessar o array e contar quantidade de (Positivos, Negativos e Zeros)
    for (numeros in arr) {
        when {
            numeros > 0 -> numerosPositivos++
            numeros < 0 -> numerosNegativos++
            else -> quantidadeDeZeros++
        }
    }

    // Fazendo a proporcao de cada numero

    val proporcaoPositiva = numerosPositivos.toDouble() / n
    val proprocaoNegativa = numerosNegativos.toDouble() / n
    val proporcaoZero = quantidadeDeZeros.toDouble() / n


    // Imprimindo cada proprocao na tela
    println(proporcaoPositiva)
    println(proprocaoNegativa)
    println(proporcaoZero)

    println("Quantidade de itens que ficou dentro da lista: $n")
}

fun main(args: Array<String>) {
    val n = readLine()!!.trim().toInt()
    println("Quantidade de itens dentro da lista $n")
    val arr = readLine()!!.trimEnd().split(" ").map { it.toInt() }.toTypedArray()
    plusMinus(arr)
}

