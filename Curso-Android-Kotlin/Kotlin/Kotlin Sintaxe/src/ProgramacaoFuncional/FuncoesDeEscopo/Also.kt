package ProgramacaoFuncional.FuncoesDeEscopo

/**
 * A função .also no código normalmente é utilizada para executar uma ação adicional em um objeto sem alterar o objeto em si.
 * Vou mostrar um exemplo em Kotlin, onde é muito comum encontrar esse recurso:
 *
 *
 */

fun main() {
    val nomes = listOf("Alleph", "Fernanda", "Crixus", "Amora")

    nomes.map { // Map seria para transformar algo
        it.uppercase() }
        .also { println(it)  } // Executando uma ação adicional sem modificar o objeto.
    // Aqui nesse caso a ação adicional que estamos executando é apenas imprimir na tela.
}