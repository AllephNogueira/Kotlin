package com.example.aulacomponenteslistagemcolecoes.teste


fun converterMaiuscula( texto: String ) : String {
    return texto.uppercase()
}

fun main() {

    val lista = listOf("jamilton", "pedro", "ana", "maria")

    /*val novaLista = mutableListOf<String>()
    lista.forEach { nome ->
        novaLista.add( nome.uppercase() )
        //println( nome.uppercase() )
    }*/
    /*val novaLista = lista.map { nome ->
        //converterMaiuscula(nome)
        //nome.uppercase()
        //"+ $nome"
    }*/
    val novaLista = lista.map { it.uppercase() }
    println( novaLista )



    /*for( nome in lista ){
       println( nome )
    }*/
}