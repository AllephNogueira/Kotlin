package com.example.aulacomponenteslistagemcolecoes.teste

class Matematica {
    fun somar( n1: Int, n2: Int ) : Int {//Método
        return n1 + n2
    }

}

//Função
/*fun somar( n1: Int, n2: Int ) : Int {
    *//* mas poderia fazer várias outras coisas *//*
    return n1 + n2
}*/

fun calcular( funcao: (Int, Int) -> Int  ){

    val retorno = funcao(10, 10)
    println( retorno )

}

/*fun executar(){
    println("Executar")
}

fun executar2() = println("Executar")*/

/*class Botao {
    fun configurarCliqueBotao( nome: String, funcao: () -> Unit ){
        funcao()
        println("Nome: $nome")
    }
}*/

/*class Botao {
    fun configurarCliqueBotao( funcao: (String) -> Unit ){
        funcao("Jamilton")
    }
}*/

fun main() {

    /*val botao = Botao()
    botao.configurarCliqueBotao {
        println("Executou função lambda executou nome: $it")
    }*/


    //Função Lambda
    /*val funcaoLambda = { nome: String, idade: Int ->
        println("Executar nome: $nome idade: $idade")
    }

    funcaoLambda("Jamilton", 35)*/

    //calcular( ::somar )
    /*val matematica = Matematica()
    calcular( matematica::somar )*/

    /*val matematica = Matematica()
    val retorno = matematica.somar(10, 10)*/
    //val retorno = somar(10, 10)
    //println( retorno )

}