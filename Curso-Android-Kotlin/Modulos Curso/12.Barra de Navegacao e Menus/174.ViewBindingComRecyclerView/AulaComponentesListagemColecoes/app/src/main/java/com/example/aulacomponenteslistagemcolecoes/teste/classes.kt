package com.example.aulacomponenteslistagemcolecoes.teste

/*class Motorista( val nome: String ) {

    fun exibirDadosMotorista() = println("Motorista: $nome")

    inner class Caminhao( val nomeCaminhao: String ){//Classe aninhada (Nested Class)
        fun exibirDadosCaminhao() = println("Caminhao: $nomeCaminhao motorista: $nome")
    }

}*/

/*data class Pergunta( val pergunta: String, val respostaCerta: Int )*/

//Getter (conseguir, recuperar) e Setter (Configurar)
/*class Usuario(){

    var nome: String = ""
        get() {
            return field.uppercase()//Campo
        }
        set(value) {
            field = "set: $value"
        }

    var idade: Int = 0
    val maiorIdade
        get() = idade >= 18

}*/

class Usuario {
    fun salvarTelefones( vararg telefones: String ){
        for ( telefone in telefones ){
            println("telefone: $telefone")
        }
    }
}

fun main() {

    val usuario = Usuario()
    usuario.salvarTelefones(
        "(11) 995525-48754",
        "(11) 995525-48754",
        "(11) 995525-48754",
        "(11) 995525-48754",
        "(11) 995525-48754"
    )

    /*val pessoa = Pessoa()
    pessoa.salvarTelefone(
        "(11) 995525-48754",
        "(11) 995525-48754",
        "(11) 995525-48754",
        "(11) 995525-48754",
        "(11) 995525-48754",
    )*/

    /*val usuario = Usuario()
    usuario.nome = "Pedro"
    usuario.idade = 15

    println("nome: ${usuario.nome} idade: ${usuario.idade} maiorIdade: ${usuario.maiorIdade}")
*/
    /*val pessoa = Pessoa()
    pessoa.nome = "Jamilton"

    println("nome: ${pessoa.nome}")*/
    /*val pergunta1 = Pergunta("Qual a pergunta?", 1)
    val pergunta2 = Pergunta("Qual a pergunta?", 2)

    val (pergunta, resposta) = pergunta1

    println(pergunta)
    println(resposta)*/

    //println( pergunta1 == pergunta2 )

    /*val motorista = Motorista("Jamilton")
    val caminhao = motorista.Caminhao("FH 60")
    caminhao.exibirDadosCaminhao()*/

    //val motorista = Motorista("Jamilton")
    /*val caminhao = Motorista.Caminhao("FH 60")
    caminhao.exibirDadosCaminhao()*/

    /*val motorista = Motorista("Jamilton")
    motorista.exibirDadosMotorista()*/

}