package com.example.aulacomponenteslistagemcolecoes.teste

data class Carro(
    val nome: String,
    val marca: String
)

fun main() {

    //val lista = setOf("jamilton", "ana", "pedro", "ana")
    //val lista = mutableSetOf("jamilton", "ana", "pedro", "ana")
    val lista = mutableSetOf(
        Carro("Gol", "Volkswagem"),
        Carro("Brasilia", "Volkswagem")
    )
    lista.add( Carro("Astra", "Chevrolet") )
    //val novaLista = lista.plus("ana")
    //println( lista.size )
    //println( lista.indexOf("ana") )
    //lista.add("mariana")
    //lista.remove("ana")
    //lista.clear()
    lista.forEach { carro ->
        println( carro.nome )
    }

}