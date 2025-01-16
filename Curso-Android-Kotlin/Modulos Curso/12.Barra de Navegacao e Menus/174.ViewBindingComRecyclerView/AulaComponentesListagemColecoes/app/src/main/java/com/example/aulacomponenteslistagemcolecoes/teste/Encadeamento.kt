package com.example.aulacomponenteslistagemcolecoes.teste

class Mensagem(
    val mensagem: String,
    val duracao: Int
) {

    companion object {

        const val DURACAO_CURTA = 0
        const val DURACAO_LONGA = 1

        fun construirTexto( mensagem: String, duracao: Int ) : Mensagem {
            return Mensagem( mensagem, duracao )
        }
    }

    fun exibir(){
        println("M: ${this.mensagem} - ${this.duracao}")
    }

}

fun main() {

    Mensagem.construirTexto(
        "Olá",
        Mensagem.DURACAO_LONGA
    ).exibir()

}