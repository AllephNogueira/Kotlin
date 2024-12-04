package ProgramacaoFuncional.FuncoesDeEscopo


/**
 * Run -> Util para inicializar objetos
 * With -> Parecido com o run(executar algo), é usado para alterar propriedades de um objeto, mas tem uma sintaxe diferente.
 */

data class ProdutoRun( var nome:String, var preco: Double) {

    fun desativarProduto () {
        println("Produto $nome, com o preço $preco foi desativado")
    }
}


fun main() {
    var produto: ProdutoRun? = null

    produto = ProdutoRun("Notebook", 1300.00)


    with(produto) {
        /**
         * With(com)
         * Com (produto) faça alguma coisa
         */

        desativarProduto()

    }


    // Fazendo with com retorno
    val novoObjeto = with(produto) {
        desativarProduto()
        this // Diferente do let que usamos o parametro, aqui usamos apenas o this, para retornar algo.
    }






}