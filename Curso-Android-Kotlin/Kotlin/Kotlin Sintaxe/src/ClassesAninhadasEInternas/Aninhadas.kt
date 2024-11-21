package ClassesAninhadasEInternas

/**
 * Classes aninhadas e classes internas.
 *
 * Classes aninhadas (Nested Class)
 *
 * Classes internas (Inner Class)
 */

class Motorista (val nome: String) {

    fun exibirDadosMotorista() = println("Motorista: $nome")

    // Classe aninhada
    class Caminhao(val nomeCaminhao: String) {
        fun exibirDadosCaminhao() = println("Caminhao: $nomeCaminhao")
    }
}

fun main() {

    /**
     * Agora imagina que voce quer utilizar a classe caminhao, mas ela esta dentro de motorista
     * Voce nao precisa instancar as duas, mas fazer o encadeamento dessa forma.
     *
     * A classe caminhao, esta dentro de motorista, mas ela funciona independente de motorista, voce precisa entrar em motorista apenas para acessar ela.
     * Ela nao se comunica diretamente com o motorista.
     */

    val dados = Motorista.Caminhao("FH 60")
    dados.exibirDadosCaminhao()

}