package Interfaces

interface Presidenciavel {
    fun candidatarSe()
}

abstract class Pessoa {
    var nome: String = ""

    fun comer() = print("Comendo...")
}


class DesenvolvedorAndroid : Pessoa(), Presidenciavel { // Herança + Interface
    fun programar() = print("Programando")
    override fun candidatarSe() {
        println("Não pode ter crimes no CPF")
    }
}


class DesenvolvedorWeb : Pessoa() {
    fun programar() = print("Programando")

}


class Jornalista : Pessoa(){
    fun escreverNoticia() = print("Escrevendo noticia")

}


class FuncionarioPublico :  Presidenciavel { // Interface sem Herança
    override fun candidatarSe() {
        println("Nao pode esta no cargo a mais de 5 anos.")
    }
}


fun main() {

    val desenvolvedorAndroid = DesenvolvedorAndroid()
    desenvolvedorAndroid.comer()

    println("-----------------------------")

    val desenvolvedorWeb = DesenvolvedorWeb()
    desenvolvedorWeb.comer()
}