package ClassesAninhadasEInternas


/**
 * Classes aninhadas e classes internas.
 *
 * Classes aninhadas (Nested Class)
 *
 * Classes internas (Inner Class)
 */

class Motorista2 (val nome: String) {

    fun exibirDadosMotorista() = println("Motorista: $nome")

    // Classe interna
    inner class Caminhao(val nomeCaminhao: String) {
        fun exibirDadosCaminhao() = println("Caminhao: $nomeCaminhao com o motorista $nome")
    }
}

fun main() {

    /**
     * Agora a classe interna ela tem uma comunicação com a classe Motorista.
     * Agora precisamos instanciar a classe motorista, depois instancar a classe caminhao também.
     * E ai sim podemos utilizar a classe caminhao, porque ela é uma classe interna, então ela responde a classe que esta fora.
     */

    val motorista = Motorista2("Alleph")
    val caminhao = motorista.Caminhao("Ford 600")
    caminhao.exibirDadosCaminhao()

}