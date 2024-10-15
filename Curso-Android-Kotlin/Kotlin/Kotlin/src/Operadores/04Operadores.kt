package Operadores

fun main() {
    val n1 = 10
    val n2 = 20
    val n3 = 2


    // Tudo que estiver dentro do parenteses vai ser feito primeiro ()
    val resultado = (n1 + n2) * n3

    println(resultado)


    // Operadores logicos
    // == - Igual
    // != - Diferente
    // ! - Negacao
    // && os dois precisam ser verdadeiro
    // || um ou outro precisa ser verdadeiro

    val operadorLogico = 2 > 1 || 10 > 50
    println(operadorLogico)
}