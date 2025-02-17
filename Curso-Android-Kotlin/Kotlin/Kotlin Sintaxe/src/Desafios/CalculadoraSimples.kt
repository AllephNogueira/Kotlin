package Desafios

/*
Desafio: Calculadora Simples
Crie um programa em Kotlin que funcione como uma calculadora simples. Ele deve ser capaz de realizar as seguintes operações:

Adição

Subtração

Multiplicação

Divisão

Requisitos:
O programa deve pedir ao usuário para inserir dois números.

O programa deve pedir ao usuário para escolher a operação (adição, subtração, multiplicação, ou divisão).

O programa deve exibir o resultado da operação escolhida.

O programa deve tratar possíveis erros, como divisão por zero.
 */

fun main() {

    println("Digite um operador: +, -, *, / ")
    var operador = readLine() /*   +, -, *, /   */

    println("Digite o primeiro valor: ")
    var numero1 = readLine()!!.toInt()
    println("Digite o segundo valor: ")
    var numero2 = readLine()!!.toInt()


    when (operador) {
        "+" -> soma(numero1, numero2)
        "-" -> subtracao(numero1, numero2)
        "*" -> multiplicacao(numero1, numero2)
        "/" -> divisao(numero1, numero2)
    }

}
fun soma(numero1: Int, numero2: Int)  {
    val total = numero1 + numero2
    println("A soma do $numero1 + $numero2 = $total")
}

fun subtracao(numero1: Int, numero2: Int)  {
    val total = numero1 - numero2
    println("A subtração do $numero1 - $numero2 = $total")
}

fun multiplicacao(numero1: Int, numero2: Int) {
    val total = numero1 * numero2
    println("A multiplicação do $numero1 * $numero2 = $total")
}

fun divisao (numero1: Int, numero2: Int) {

    if (numero1 != 0 && numero2 != 0) {
        val total = numero1 / numero2
        println("A divisao do $numero1 / $numero2 = $total")
    }else {
        println("Operação não permitida numero nao pode ser divisivel por 0")
    }


}
