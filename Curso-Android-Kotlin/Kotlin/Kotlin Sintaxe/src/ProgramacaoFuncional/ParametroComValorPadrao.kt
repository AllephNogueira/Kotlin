package ProgramacaoFuncional

fun calculo(numero1 : Int, numero2: Int = 0) : Int = numero1 + numero2

fun main() {
    val calcular = calculo(10)
    println(calcular) // Saida 10
}