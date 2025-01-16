package com.example.aulacomponenteslistagemcolecoes.teste

fun main() {

    val listaLanches = listOf("Hambúrguer", "Bata frita")
    val listaEntradas = listOf("Coxa de frango", "Pipoca")

    //val novaLista = listaLanches.union( listaEntradas )
    val listaExclusao = listOf("Bata frita", "Pipoca")
    val novaLista = listaEntradas + listaLanches
    val listaCompleta = novaLista - listaExclusao
    println( listaCompleta )

}