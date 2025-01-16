package com.example.aulacomponenteslistagemcolecoes.teste


fun main() {

    val lista = arrayOf("jamilton", "maria", "pedro", "ana")
    //println( lista[0] )
    //println( lista.size )
    //println( lista.indexOf("jamilton") )
    //println( lista.first() )
    //println( lista.last() )
    //println( lista.contains("joão") )


    //val novaLista = lista.plus( "joão" )
    lista.shuffle()
    lista.forEach { item ->
        println( item )
    }

}