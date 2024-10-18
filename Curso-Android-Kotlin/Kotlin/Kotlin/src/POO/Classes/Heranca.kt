package POO.Classes

open class Animal(var cor: String = "", var nome: String = "", var tamanho: String = "") {

    // Open = aberto para ser usado
    open fun somAnimal() = println("Som..")

    // Override reescrevendo um metodo
    override fun toString(): String {
        return "$nome (cor: $cor, tamanho: $tamanho)"
    }


}
// Repara que quando a classe pai possui um construtor, a classe filha também deve ter.
class Cachorro(cor: String, nome: String, tamanho: String) : Animal(cor, nome, tamanho) {


    // Override estamos reescrevendo o metodo
    override fun somAnimal(){ // Herdando com polimorfismo
        super.somAnimal()
        println("latindo...")
    }
}

class Gato(cor: String, nome: String, tamanho: String) : Animal(cor, nome, tamanho) {

    // Override estamos reescrevendo o metodo
    override fun somAnimal() {
        super.somAnimal()
        println("miando...")
    }
}


fun main() {
    val cachorro = Cachorro("Branco/Amarelo", "Crixus", "Pequeno")
    println(cachorro)
    println(cachorro.somAnimal())



    val gato = Gato("Marom", "Anastacia", "Pequeno")
    println(gato.somAnimal())
    println(gato)

}