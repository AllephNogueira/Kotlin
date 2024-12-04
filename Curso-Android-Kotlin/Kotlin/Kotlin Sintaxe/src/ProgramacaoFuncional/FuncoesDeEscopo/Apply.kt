package ProgramacaoFuncional.FuncoesDeEscopo

/** Apply
 * Usado para aplicar configurações em objetos.
 *
 * Quero aplicar um valor extra
 * Use o apply.
 *
 */

class alertaMensagem {

    fun configurarTitulo( titulo: String ) = println(titulo)
    fun configurarDescricao( descricao: String ) = println(descricao)
    fun configurarConfirmar(  ) = println("Ação de confirmar")
    fun configurarCancelar( ) = println("Ação de cancelar")
}

fun main() {

    /**
     * Imagina que estamos configurando o card, ontem tem o titulo, descrição, cancelar e salvar..
     * Podemos fazer dessa forma, mas tem uma forma melhor.
     */
    val alertaMensagem = alertaMensagem()
//    alertaMensagem.configurarTitulo("Confirmar salvar?")
//    alertaMensagem.configurarDescricao("Você tem certeza que deseja salvar?")
//    alertaMensagem.configurarCancelar()
//    alertaMensagem.configurarConfirmar()

    /**
     * Usando a funçao de escopo para deixar o codigo mais simples.
     */

    alertaMensagem.apply { // apply = estamos aplicando algo...
        alertaMensagem.configurarTitulo("Confirmar salvar?")
        alertaMensagem.configurarDescricao("Você tem certeza que deseja salvar?")
        alertaMensagem.configurarCancelar()
        alertaMensagem.configurarConfirmar()
    }


}