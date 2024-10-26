package POO.Classes

abstract class Pessoa (var nome: String = "", var cpf: String = "", var sexo: String = "") {

    open fun opcaoBanheiro() = print("Vou ao banheiro ")

    override fun toString(): String {
        return "Pessoa(nome='$nome', cpf='$cpf', sexo='$sexo')"
    }


    abstract fun dormir()
}

class Homem(nome: String, cpf: String, sexo: String) : Pessoa(nome, cpf, sexo) {

    override fun opcaoBanheiro() {
        super.opcaoBanheiro()
        println("masculinho")
    }

    override fun dormir() {
        println("Indo dormir com barriga para baixo")
    }
}


class Mulher(nome: String, cpf: String, sexo: String) : Pessoa(nome, cpf, sexo) {

    override fun opcaoBanheiro() {
        super.opcaoBanheiro()
        println("toalete")
    }

    override fun dormir() {
        println("Indo dormir com barriga para cima")
    }


}

fun main() {

    val homem = Homem("alleph", "15679025701", "Masculinho")
    println(homem)
    homem.opcaoBanheiro()
    homem.dormir()

}