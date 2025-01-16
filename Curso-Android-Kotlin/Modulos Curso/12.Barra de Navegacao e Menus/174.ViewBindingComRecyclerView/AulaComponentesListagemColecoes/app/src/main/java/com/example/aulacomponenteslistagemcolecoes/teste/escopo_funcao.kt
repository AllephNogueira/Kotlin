package com.example.aulacomponenteslistagemcolecoes.teste


class Pessoa {

    val nome = ""//Escopo local

}

var nome = "jamilton"//Escopo global

fun executar(){

    //val nome = "ana"//Escopo local
    nome = "executou novo nome ana"

}

fun main() {


    executar()
    nome = "voltou para jamilton"
    println( nome )

}