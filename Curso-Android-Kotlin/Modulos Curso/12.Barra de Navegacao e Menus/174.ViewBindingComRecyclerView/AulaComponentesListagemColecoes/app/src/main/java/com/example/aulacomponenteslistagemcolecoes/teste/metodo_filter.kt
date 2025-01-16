package com.example.aulacomponenteslistagemcolecoes.teste

fun main() {

    //val listaFrutas = listOf("maçã", "laranja", "banana")
    //val listaVendas = listOf(100, 500, 150, 1500, 800, 650)
    val nomes = listOf("jamilton", "pedro", "ana")

    /*val novaLista = listaFrutas.filter { fruta ->
        fruta == "laranja"
    }*/

    /*val novaLista = listaVendas.filter { valor ->
        valor >= 600// condição (true ou false)
    }*/
    //val novaLista = listaVendas.filter {it >= 500}
    val novaLista = nomes.filter { nome ->
        nome.contains("a")// true false
    }
    println( novaLista )


}