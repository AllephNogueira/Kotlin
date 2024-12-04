package ProgramacaoFuncional.FuncoesDeEscopo

/**
 * Run -> Util para inicializar objetos
 *
 * Onde usar? como usar? praque usar?
 * Run = É para quando voce esta executando alguma coisa.
 * Ex: Quero salvar algo no banco de dados?
 * Use o run, voce esta executando uma tarefa para salvar.
 */

data class ProdutoRun( var nome:String, var preco: Double) {
    fun desativarProduto () {
        println("Produto $nome, com o preço $preco foi desativado")
    }
}


fun main() {
    var produto: produtoWith? = null

    produto = produtoWith("Notebook", 1300.00)


    /**
     * Diferenças de Let para Run
     */


    produto?.let { produto ->
        /**
         * Quando utilizamos o let, podemos utilizar o objeto produto inteiro
         */

    }

    produto?.run {
        /**
         * Ja no run, é como se a gente estivesse dentro da propria classe do produto, podemos utilizar todos os seus metodos e atributos.
         * Aqui poderiamos utilizar o this.desativar(), mas nao precisa.
         * Repara como fica facil de manipular a classe e utilizar seus metodos.
         */
        desativarProduto()
    }



}