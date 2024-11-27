package ProgramacaoFuncional.EscopoFuncao

/**
 * Oque é um escopo de funçao
 *
 * Imagina que temos uma casa, e dentro dessa casa tem os comodos
 * Imagina que temos uma função chamada quarto, e la temos um robo
 * Nome da função é ligarRobor()
 * Dentro dessa função vamos ter uma variavel responsavel por isso
 * Então essa variavel só vai poder ser vista dentro dessa função, se ela for realmente criada la dentro.
 * Porque também podemos ter variaveis sendo criadas do lado de fora e sendo usada dentro.
 */

val nome = "Alleph" // escopo global

class Pessoa {
    val nome: String = ""
}

fun quarto() {
    // Aqui e nossa função que fizemos a cima, aqui só quem consegue ver o escopo é quem ta dentro do quarto
    // Oque seria escopo? Seria todo o bloco de codigo que esta aqui dentro.
    val nome = "" // escopo local
}

fun main() {


    println(nome)



}