package Varags

/**
 * Oque é?
 * Imagina que queremos salvar varios telefones, então para isso vamos usar o Varags
 * Atenção: O parametro varags tem que esta no final.
 *
 * Imagina que estamos salvando o telefone de uma pessoa e ele tem mais de 1 contato.
 */

class meuNumero {

    fun salvarTelefones(vararg telefones: String){
        for (telefone in telefones){
            println(telefone)
        }
    }

}

fun main() {

    /**
     * Aqui estamos fazendo com Java, agora vamos com Kotlin
     */
    val pessoa = VaragsJava()
    pessoa.salvarTelefone(21, "97557-5694", "98818-0461", "98030-1853")


    /**
     * Agora com Kotlin
     */
    val pessoa2 = meuNumero()
    pessoa2.salvarTelefones("(21)-97557-5694", "(21)-98818-0461")
}